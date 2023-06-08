package com.example.plannerscheduler.dto;

import com.example.plannerscheduler.enums.UserRole;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDtoRequest extends UserDtoRequest{

    Long groupId;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setUserRole(){
        this.userRole = UserRole.STUDENT;
    }

}
