package com.example.plannerscheduler.service.interfaces;

import com.example.plannerscheduler.models.Note;
import org.springframework.stereotype.Service;

@Service
public interface NoteServiceInterface {
    Note getById(Long id);
}
