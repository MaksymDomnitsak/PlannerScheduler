package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.User;
import com.example.plannerscheduler.repository.UserRepository;
import com.example.plannerscheduler.service.interfaces.UserServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    UserRepository userRepository;

    BCryptPasswordEncoder encoder;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("No user with email in DB:",email));
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Page<User> getAll(Pageable pageable)
    {
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User createUser(User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(Long id, User newUser){
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        newUser.setId(id);
        newUser.setCreatedAt(user.getCreatedAt());
        return userRepository.save(newUser);

    }
}

