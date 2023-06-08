package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.UserDtoRequest;
import com.example.plannerscheduler.dto.UserDtoResponse;
import com.example.plannerscheduler.mappers.UserToUserDtoMapper;
import com.example.plannerscheduler.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    private UserToUserDtoMapper mapper = Mappers.getMapper(UserToUserDtoMapper.class);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('TEACHER')")
    public ResponseEntity<List<UserDtoResponse>> getAll() {
        List<UserDtoResponse> userList = new ArrayList<>();
        userService.getAll().forEach(user -> userList.add(mapper.userToUserDto(user)));
        return ResponseEntity.ok(userList);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('TEACHER')")
    public ResponseEntity<List<UserDtoResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<UserDtoResponse> userList = new ArrayList<>();
        userService.getAll(pageable).forEach(user -> userList.add(mapper.userToUserDto(user)));
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id){
        UserDtoResponse response = mapper.userToUserDto(userService.getById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserDtoResponse createUser(@RequestBody UserDtoRequest user){
        return mapper.userToUserDto(userService.createUser(mapper.userDtoToUser(user)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteUser(@PathVariable("id") Long id)
    {
        userService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#id == authentication.principal.id or hasAnyAuthority('ADMIN')")
    public UserDtoResponse updateUser(@PathVariable("id") Long id,
                           @RequestBody UserDtoRequest user){
        return mapper.userToUserDto(userService.updateUser(id, mapper.userDtoToUser(user)));
    }

}
