package com.crud.crud.controllers;

import com.crud.crud.controllers.dto.CreateTaskDTO;
import com.crud.crud.entities.TaskEntity;
import com.crud.crud.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService ;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
       var tasks = taskService.getTasks();
       return ResponseEntity.ok(tasks); //if we get the tasks
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTasksById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task); //if we get the tasks
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body){
       var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());

        return ResponseEntity.ok(task);
    }
}
