package com.example.plannerscheduler.mappers;

import com.example.plannerscheduler.dto.CustomScheduleDtoResponse;
import com.example.plannerscheduler.dto.ScheduleDtoRequest;
import com.example.plannerscheduler.dto.ScheduleDtoResponse;
import com.example.plannerscheduler.models.Schedule;
import com.example.plannerscheduler.service.interfaces.ScheduleServiceInterface;
import com.example.plannerscheduler.service.interfaces.StudentServiceInterface;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleDtoToScheduleMapper {

    ScheduleDtoResponse scheduleToDtoSchedule(Schedule schedule);

    CustomScheduleDtoResponse scheduleToCustomDtoSchedule(Schedule schedule);
}
