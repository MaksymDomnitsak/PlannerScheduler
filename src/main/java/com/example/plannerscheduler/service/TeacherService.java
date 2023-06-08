package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.Teacher;
import com.example.plannerscheduler.repository.TeacherRepository;
import com.example.plannerscheduler.service.interfaces.TeacherServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements TeacherServiceInterface {
    TeacherRepository teacherRepository;


    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    public Page<Teacher> getAll(Pageable pageable)
    {
        Page<Teacher> page = teacherRepository.findAll(pageable);
        return page;
    }

    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Transactional
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public Teacher createTeacher(Teacher newTeacher) {
        return teacherRepository.save(newTeacher);
    }

    @Transactional
    public Teacher updateTeacher(Long id, Teacher newTeacher){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(RuntimeException::new);
        newTeacher.setId(id);
        newTeacher.setCreatedAt(teacher.getCreatedAt());
        return teacherRepository.save(newTeacher);

    }
}
