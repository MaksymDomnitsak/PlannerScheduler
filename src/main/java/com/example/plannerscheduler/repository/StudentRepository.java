package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.models.Student;
import com.example.plannerscheduler.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "select st from Student st where Group = :groupId")
    List<Student> findAllByGroupId(@Param(value = "groupId") Long groupId);

    Page<Student> findStudentsByGroupId(@Param(value = "groupId") Long groupId, Pageable pageable);

    Student findByEmail(String email);

    @Query("select item.group.name from Student item where item.id = :userId")
    String findGroupNameByUserId(Long userId);
}
