package com.meisters.api.Tasks.Model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column()
    @Length(min = 1, max = 50)
    @NotBlank
    @NotNull
    String title;

    @Column()
    @Length(max = 255)
    @NotBlank
    String description;

    @Column()
    @NotNull
    boolean status;
}
