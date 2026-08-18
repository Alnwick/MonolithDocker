package com.alnwick.MonolithDocker.Controller;

import com.alnwick.MonolithDocker.Dto.TaskCreateDto;
import com.alnwick.MonolithDocker.Dto.TaskListDto;
import com.alnwick.MonolithDocker.Dto.TaskUpdateDto;
import com.alnwick.MonolithDocker.Model.TaskEntity;
import com.alnwick.MonolithDocker.Service.ITaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> allTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> taskById(@RequestParam Long id){
        Optional<TaskEntity> task = taskService.findById(id);

        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskCreateDto taskDto){
        TaskEntity newTask = taskService.save(taskDto);

        return ResponseEntity.ok(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@RequestParam Long id,
            @RequestBody TaskUpdateDto taskDto){

        TaskEntity task = taskService.updateTask(id, taskDto);

        if(task != null){
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@RequestParam Long id){

        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
