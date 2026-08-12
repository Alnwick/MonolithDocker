package com.alnwick.MonolithDocker.Service;

import com.alnwick.MonolithDocker.Dto.TaskCreateDto;
import com.alnwick.MonolithDocker.Dto.TaskListDto;
import com.alnwick.MonolithDocker.Dto.TaskUpdateDto;
import com.alnwick.MonolithDocker.Model.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    List<TaskListDto> findAll();
    Optional<TaskEntity> findById(Long id);
    TaskEntity save(TaskCreateDto taskCreateDto);
    TaskEntity updateTask(Long id, TaskUpdateDto taskUpdateDto);
    void deleteById(Long id);

}
