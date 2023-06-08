package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleServiceInterface {
    Schedule getById(Long id);
}
