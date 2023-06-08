package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.mappers.StudentDtoToStudentMapper;
import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.repository.StudentRepository;
import com.example.plannerscheduler.repository.StudentRepository;
import com.example.plannerscheduler.service.interfaces.StudentServiceInterface;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {
    StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Page<Student> getAll(Pageable pageable)
    {
        Page<Student> page = studentRepository.findAll(pageable);
        return page;
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }
    public List<Student> getByGroup(Long groupId){
        return studentRepository.findAllByGroupId(groupId);
    }
    public Page<Student> getPageByGroup(Long groupId, Pageable pageable) {
        return studentRepository.findStudentsByGroupId(groupId, pageable);
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student createStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Student updateStudent(Long id, Student newStudent){
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        newStudent.setId(id);
        newStudent.setCreatedAt(student.getCreatedAt());
        return studentRepository.save(newStudent);

    }
}
