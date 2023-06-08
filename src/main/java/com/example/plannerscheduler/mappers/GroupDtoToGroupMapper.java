package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.GroupDto;
import com.example.plannerscheduler.dto.GroupDtoWithStudents;
import com.example.plannerscheduler.models.Group;
import org.mapstruct.Mapper;

@Mapper
public interface GroupDtoToGroupMapper {
    Group groupDtoToGroupMapper(GroupDto groupDto);

    GroupDto groupToGroupDtoMapper(Group group);

    GroupDtoWithStudents groupToGroupDtoWithStudentsMapper(Group group);

}
