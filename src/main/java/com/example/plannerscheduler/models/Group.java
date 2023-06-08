package com.example.plannerscheduler.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@Column(name = "name")
    String name;
	
    @OneToMany(mappedBy = "group")
    List<Student> students;
    public Group() {
    }

    public Group(String name){
        this.name = name;
    }

    public Group(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}