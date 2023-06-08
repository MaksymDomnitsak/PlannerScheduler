package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    User getById(Long id);
}
