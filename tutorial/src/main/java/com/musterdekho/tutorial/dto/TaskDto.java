package com.musterdekho.tutorial.dto;

import com.musterdekho.tutorial.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean status;
    private String assignTo;
}
