package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.UserDtoRequest;
import com.example.plannerscheduler.dto.UserDtoResponse;
import com.example.plannerscheduler.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserToUserDtoMapper {
    User userDtoToUser(UserDtoRequest userDto);

    UserDtoResponse userToUserDto(User user);
}
