package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherServiceInterface {

    Teacher getById(Long id);
}
