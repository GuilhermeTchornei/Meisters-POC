package com.meisters.api.Tasks.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meisters.api.Tasks.Model.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    
}
