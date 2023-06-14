package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.GroupedNote;
import com.example.plannerscheduler.dto.NoteDtoRequest;
import com.example.plannerscheduler.dto.NoteDtoResponse;
import com.example.plannerscheduler.mappers.NoteDtoToNoteMapper;
import com.example.plannerscheduler.mappers.impl.NoteDtoToNote;
import com.example.plannerscheduler.models.Group;
import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.service.GroupService;
import com.example.plannerscheduler.service.NoteService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
@RequestMapping("/api/note")
@Slf4j
public class NoteController {
    NoteService noteService;
    NoteDtoToNoteMapper mapper = Mappers.getMapper(NoteDtoToNoteMapper.class);
    NoteDtoToNote toObjectMapper;

    GroupService groupService;


    public NoteController(NoteService noteService, NoteDtoToNote toObjectMapper, GroupService groupService) {
        this.noteService = noteService;
        this.toObjectMapper = toObjectMapper;
        this.groupService = groupService;
    }

    @GetMapping(params = {"studentId"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public ResponseEntity<List<NoteDtoResponse>> getNotesByStudentId(@RequestParam("studentId") Long studentId){
        List<NoteDtoResponse> noteList = new ArrayList<>();
        noteService.getByStudentId(studentId).forEach(note -> noteList.add(mapper.noteToDtoNote(note)));
        return ResponseEntity.ok(noteList);
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<NoteDtoResponse>> getAll() {
        List<NoteDtoResponse> noteList = new ArrayList<>();
        noteService.getAll().forEach(note -> noteList.add(mapper.noteToDtoNote(note)));
        return ResponseEntity.ok(noteList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<NoteDtoResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<NoteDtoResponse> noteList = new ArrayList<>();
        noteService.getAll(pageable).forEach(note -> noteList.add(mapper.noteToDtoNote(note)));
        return ResponseEntity.ok(noteList);
    }


    @GetMapping(params = {"isFinished"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NoteDtoResponse>> getNotesByFinishedIs(@RequestParam("isFinished") Boolean isFinished) {
        List<NoteDtoResponse> noteList = new ArrayList<>();
        noteService.getByFinishedIs(isFinished).forEach(note -> noteList.add(mapper.noteToDtoNote(note)));
        return ResponseEntity.ok(noteList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('STUDENT')")
    public ResponseEntity<NoteDtoResponse> getNoteById(@PathVariable("id") Long id){
        NoteDtoResponse response = mapper.noteToDtoNote(noteService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoteDtoResponse> createNote(@RequestBody NoteDtoRequest note){
        return ResponseEntity.ok(mapper.noteToDtoNote(noteService.createNote(toObjectMapper.noteDtoToNote(note))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN') or #noteService.getById(id).student.id == authentication.principal.id")
    public void deleteNote(@PathVariable("id") Long id)
    {
        noteService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN') or #note.student_id == authentication.principal.id")
    public ResponseEntity<NoteDtoResponse> updateNote(@PathVariable("id") Long id,
                                              @RequestBody NoteDtoRequest note){
        return ResponseEntity.ok(mapper.noteToDtoNote(noteService.updateNote(id, toObjectMapper.noteDtoToNote(note))));
    }

    @PostMapping("/groupCreate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NoteDtoResponse>> createGroupedNote(@RequestBody GroupedNote note){
        List<NoteDtoResponse> noteList = new ArrayList<>();
        List<Long> studentsId = new ArrayList<>();
        if(note.getStudents().length != 0){
            studentsId = Arrays.stream(note.getStudents()).toList();
        }
        if(note.getGroups().length != 0) {
            for (Long id : note.getGroups()) {
                Group curr = groupService.getById(id);
                for (Student stud : curr.getStudents()) {
                    NoteDtoRequest currNote = new NoteDtoRequest(note.getTitle(), note.getEvent(), stud.getId(), note.getBody(), false);
                    noteList.add(mapper.noteToDtoNote(noteService.createNote(toObjectMapper.noteDtoToNote(currNote))));
                    if (studentsId.size() != 0 && studentsId.contains(stud.getId())) {
                        studentsId.remove(stud.getId());
                    }
                }
            }
        }
        if(studentsId.size() != 0){
            for(Long id: studentsId){
                NoteDtoRequest currNote = new NoteDtoRequest(note.getTitle(),note.getEvent(),id,note.getBody(),false);
                noteList.add(mapper.noteToDtoNote(noteService.createNote(toObjectMapper.noteDtoToNote(currNote))));
            }
        }

        return ResponseEntity.ok(noteList);
    }



}
