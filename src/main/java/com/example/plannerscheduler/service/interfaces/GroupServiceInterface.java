package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.Group;
import org.springframework.stereotype.Service;

@Service
public interface GroupServiceInterface {
    Group getById(Long id);
}
