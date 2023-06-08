package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.UserDtoResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/test", produces = MediaType.TEXT_PLAIN_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TestController {
    @GetMapping("Callback")
    @ResponseStatus(HttpStatus.OK)
    public String getAll() {
        return "Hello";
    }
}
