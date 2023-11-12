package com.meisters.api.Tasks.DTO;

import com.meisters.api.Tasks.Enum.StatusEnum;

import jakarta.validation.constraints.NotNull;

public record StatusDto(
    @NotNull
    StatusEnum status
) {}
