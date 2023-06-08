package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.models.Subject;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsSubjectByName(String name);

    Subject findByName(String name);
}
