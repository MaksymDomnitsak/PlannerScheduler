package com.example.plannerscheduler.service;

import com.example.plannerscheduler.exception.ObjectNotFoundException;
import com.example.plannerscheduler.models.Note;
import com.example.plannerscheduler.repository.NoteRepository;
import com.example.plannerscheduler.service.interfaces.NoteServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Page<Note> getAll(Pageable pageable)
    {
        Page<Note> page = noteRepository.findAll(pageable);
        return page;
    }

    @Transactional
    public Note createNote(Note newNote) {
        newNote.setFinished(false);
        return noteRepository.save(newNote);
    }

    public List<Note> getByStudentId(Long studentId){
        return noteRepository.findAllByStudentId(studentId);
    }

    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    public List<Note> getByFinishedIs(boolean isFinished){
        return noteRepository.findAllByIsFinished(isFinished);
    }



    @Transactional
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Transactional
    public Note updateNote(Long id, Note newNote){
        Note note = noteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Note not found with id ",id));
        newNote.setId(id);
        newNote.setCreatedAt(note.getCreatedAt());
        return noteRepository.save(newNote);

    }
}
