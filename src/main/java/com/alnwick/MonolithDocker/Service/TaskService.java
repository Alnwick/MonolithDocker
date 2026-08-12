package com.alnwick.MonolithDocker.Service;

import com.alnwick.MonolithDocker.Dto.TaskCreateDto;
import com.alnwick.MonolithDocker.Dto.TaskListDto;
import com.alnwick.MonolithDocker.Dto.TaskUpdateDto;
import com.alnwick.MonolithDocker.Model.StatusTask;
import com.alnwick.MonolithDocker.Model.TaskEntity;
import com.alnwick.MonolithDocker.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService{

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskListDto> findAll() {

        return taskRepository.findAll()
                .stream()
                .map(t -> new TaskListDto(t.getId(), t.getTitle(), t.getStatus().toString()))
                .toList();
    }

    @Override
    public Optional<TaskEntity> findById(Long id) {

        return taskRepository.findById(id);
    }

    @Override
    public TaskEntity save(TaskCreateDto taskCreateDto) {
        TaskEntity task  = new TaskEntity();
        task.setTitle(taskCreateDto.title());
        task.setDescription(taskCreateDto.description());
        task.setStatus(StatusTask.valueOf(taskCreateDto.status()));
        task.setDueDate(LocalDate.now());

        return taskRepository.save(task);
    }

    @Override
    public TaskEntity updateTask(Long id, TaskUpdateDto taskUpdateDto) {
        if(taskRepository.existsById(id)){
            TaskEntity task = taskRepository.findById(id).get();

            if(!task.getDescription().equals(taskUpdateDto.description())){
                task.setDescription(taskUpdateDto.description());
            }
            if(!task.getStatus().toString().equals(taskUpdateDto.status())){
                task.setStatus(StatusTask.valueOf(taskUpdateDto.status()));
            }
            taskRepository.save(task);
            return task;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
