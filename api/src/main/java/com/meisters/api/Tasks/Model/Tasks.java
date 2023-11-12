package com.meisters.api.Tasks.Model;

import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.meisters.api.Tasks.DTO.TasksDto;
import com.meisters.api.Tasks.Enum.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    @Length(min = 1, max = 50)
    @NotBlank
    String title;

    @Column(nullable = false)
    @Length(max = 255)
    @NotNull
    String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    StatusEnum status;

    public Tasks(TasksDto tasksDto){
        this.title = tasksDto.title();
        this.description = tasksDto.description();
        this.status = Objects.requireNonNullElse(tasksDto.status(), StatusEnum.PENDING);
    }
}
