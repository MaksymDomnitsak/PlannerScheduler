package com.example.plannerscheduler.models;

import com.example.plannerscheduler.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "teacher_id")
public class Teacher extends User {

    public Teacher() {
    }

    public Teacher(Long id, String firstName, String lastName, String patronymicName,String email, String password, UserRole userRole, String phoneNumber, Instant createdAt, Instant modifiedAt) {
        super(id, firstName, lastName, patronymicName, email, password, userRole, phoneNumber, createdAt, modifiedAt);
    }

    public Teacher(String firstName, String lastName, String patronymicName, String email, String password, UserRole userRole) {
        super(firstName, lastName, patronymicName, email, password, userRole);
    }
}