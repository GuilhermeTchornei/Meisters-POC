package com.meisters.api.Tasks.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meisters.api.Tasks.Model.Tasks;
import com.meisters.api.Tasks.Service.TasksService;

@RequestMapping("tasks")
@RestController
public class TasksController {
    private final TasksService service;

    TasksController(TasksService TasksService) {
        this.service = TasksService;
    }

    @GetMapping
    ResponseEntity<List<Tasks>> getAll() {
        List<Tasks> list = service.getAll();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @PostMapping
    void createOne() {
        System.out.println("Task created");
    }
}
