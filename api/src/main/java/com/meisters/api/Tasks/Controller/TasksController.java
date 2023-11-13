package com.meisters.api.Tasks.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meisters.api.Errors.NotFoundException;
import com.meisters.api.Tasks.DTO.StatusDto;
import com.meisters.api.Tasks.DTO.TasksDto;
import com.meisters.api.Tasks.Model.Tasks;
import com.meisters.api.Tasks.Service.TasksService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RequestMapping("tasks")
@RestController
@Validated
public class TasksController {
    private final TasksService service;

    TasksController(TasksService TasksService) {
        this.service = TasksService;
    }

    @GetMapping
    List<Tasks> getAll() {
        List<Tasks> list = service.getAll();

        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No tasks found!");
        } else {
            return list;
        }
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Tasks createOne(@RequestBody @Valid TasksDto tasksDto) {
        return service.saveOne(tasksDto);
    }

    @PatchMapping("{taskId}")
    void setStatus(@PathVariable @Valid @Positive long taskId, @RequestBody @Valid StatusDto statusDto){
        try{
            service.setStatus(taskId, statusDto.status());
        }
        catch(NotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        }
    }

    @DeleteMapping("{taskId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Task deleted successfully")
    void deleteOne(@PathVariable @Valid @Positive long taskId){
        try{
            service.deleteOne(taskId);
        }
        catch(NotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        }
    }
}
