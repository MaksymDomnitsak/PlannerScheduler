package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByEmail(String email);

    boolean existsTeacherByFirstNameAndLastNameAndPatronymicName(String firstName,String lastName,String patronymic);

    Teacher findTeacherByFirstNameAndLastNameAndPatronymicName(String firstName,String lastName,String patronymic);
}
