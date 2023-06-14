package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.Subject;
import com.example.plannerscheduler.repository.ScheduleRepository;
import com.example.plannerscheduler.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    SubjectRepository subjectRepository;

    ScheduleRepository scheduleRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ScheduleRepository scheduleRepository) {
        this.subjectRepository = subjectRepository;
        this.scheduleRepository = scheduleRepository;
    }


    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Page<Subject> getAll(Pageable pageable)
    {
        return subjectRepository.findAll(pageable);
    }

    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    public List<Subject> getByTeacherId(Long teacherId){
        return scheduleRepository.findSubjectsfromScheduleByTeacher(teacherId);
    }

    @Transactional
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Transactional
    public Subject createSubject(Subject newSubject) {
        return subjectRepository.save(newSubject);
    }

    @Transactional
    public Subject updateSubject(Long id, Subject newSubject){
        Subject subject = subjectRepository.findById(id).orElseThrow(RuntimeException::new);
        newSubject.setId(id);
        return subjectRepository.save(newSubject);

    }
}
