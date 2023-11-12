package com.meisters.api.Tasks.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meisters.api.Tasks.DTO.TasksDto;
import com.meisters.api.Tasks.Model.Tasks;
import com.meisters.api.Tasks.Respository.TasksRepository;

@Service
public class TasksService {
    private final TasksRepository repository;

    TasksService(TasksRepository tasksRepository){
        this.repository = tasksRepository;
    }
    
    public List<Tasks> getAll(){
        return repository.findAll();
    }

    public void saveOne(TasksDto tasksDto){
        Tasks task = new Tasks(tasksDto);
        repository.save(task);
    }

}
