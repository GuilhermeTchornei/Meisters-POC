package com.meisters.api.Tasks.DTO;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meisters.api.Tasks.Enum.StatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TasksDto(
    @JsonProperty("id") Long id,
    @NotNull @NotBlank @Length(min = 1, max = 50) String title,
    @NotNull @Length(max = 255) String description,
    StatusEnum status) {}
