package com.meisters.api.Tasks.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meisters.api.Errors.NotFoundException;
import com.meisters.api.Tasks.DTO.TasksDto;
import com.meisters.api.Tasks.Enum.StatusEnum;
import com.meisters.api.Tasks.Model.Tasks;
import com.meisters.api.Tasks.Repository.TasksRepository;

@Service
public class TasksService {
    private final TasksRepository repository;

    TasksService(TasksRepository tasksRepository){
        this.repository = tasksRepository;
    }
    
    public List<Tasks> getAll(){
        return repository.findAllByOrderByIdAsc();
    }

    public void saveOne(TasksDto tasksDto){
        Tasks task = new Tasks(tasksDto);
        repository.save(task);
    }

    public void setStatus(long id, StatusEnum status){
        Tasks task = repository.findById(id).orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        task.setStatus(status);
        repository.save(task);
    }

}
