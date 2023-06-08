package com.example.plannerscheduler.repository;

import com.example.plannerscheduler.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    boolean existsGroupByName(String groupNumber);
}
