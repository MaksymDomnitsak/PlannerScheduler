package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentServiceInterface {
    Student getById(Long id);
}
