package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByIsFinished(Boolean isFinished);

    @Query(value = "select ls from Note ls where ls.student.id = :studentId order by ls.createdAt ASC")
    List<Note> findAllByStudentId(Long studentId);
}
