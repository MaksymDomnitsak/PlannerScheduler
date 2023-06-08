package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.repository.GroupRepository;
import com.example.plannerscheduler.service.interfaces.GroupServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements GroupServiceInterface {

    GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() { return groupRepository.findAll(); }

    public Page<Group> getAll(Pageable pageable) { return groupRepository.findAll(pageable); }

    public Group getById(Long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Transactional
    public void delete(Long id){
        groupRepository.deleteById(id);
    }

    @Transactional
    public Group createGroup(Group group){
        return groupRepository.save(group);
    }

    @Transactional
    public Group updateGroup(Long id,Group group){
        Group oldGroup = groupRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Group not found with id ",id));
        group.setId(id);
        return groupRepository.save(group);
    }

    }
