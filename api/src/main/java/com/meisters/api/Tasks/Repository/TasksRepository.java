package com.meisters.api.Tasks.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meisters.api.Tasks.Model.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    public List<Tasks> findAllByOrderByIdAsc();
}
