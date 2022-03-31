package com.example.todolist.controller;

import java.util.List;

import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {
    
    TaskService taskService;

    @ApiOperation(value = "Creating new task")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "task created"),
        @ApiResponse(code = 500, message = "error while creating task"),
    })
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Creating new task");
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Listing new task")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "listing tasks"),
        @ApiResponse(code = 500, message = "error while listing tasks"),
    })
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        log.info("Listing all tasks");
        return taskService.listAllTasks();
    }

    @ApiOperation(value = "getting a task")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "found a task"),
        @ApiResponse(code = 404, message = "error while fetching task"),
    })
    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id) {
        log.info("Getting task by id [{}]", id);
        return taskService.findTaskById(id);
    }

    @ApiOperation(value = "updating a task")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "task updated"),
        @ApiResponse(code = 500, message = "error while updating task"),
    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        log.info("Updating task by id [{}] with new info [{}]", id, task);
        return taskService.updateTaskById(task, id);
    }

    @ApiOperation(value = "deleting a task")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "task deleted"),
        @ApiResponse(code = 500, message = "error while deleting task"),
    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Deleting task");
        return taskService.deleteTask(id);
    }
}
