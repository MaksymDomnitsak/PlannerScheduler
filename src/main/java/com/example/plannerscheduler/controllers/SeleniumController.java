package com.example.plannerscheduler.controllers;

import com.example.plannerscheduler.dto.GroupDto;
import com.example.plannerscheduler.mappers.GroupDtoToGroupMapper;
import com.example.plannerscheduler.service.SeleniumService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/api/parse", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SeleniumController {
    SeleniumService service;

    private GroupDtoToGroupMapper mapper = Mappers.getMapper(GroupDtoToGroupMapper.class);

    @Autowired
    public SeleniumController(SeleniumService service) {
        this.service = service;
    }

    @GetMapping("/loadGroups")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<GroupDto>> loadGroups(){
        List<GroupDto> groupList = new ArrayList<>();
        service.loadGroups().forEach(group -> groupList.add(mapper.groupToGroupDtoMapper(group)));
        return ResponseEntity.ok(groupList);
    }

    @GetMapping("/loadAll")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity loadSchedule(){
        service.getFromSchedule();
        return (ResponseEntity) ResponseEntity.ok();
    }

}
