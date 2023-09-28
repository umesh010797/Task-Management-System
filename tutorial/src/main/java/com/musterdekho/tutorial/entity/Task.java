package com.musterdekho.tutorial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
   // @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence_name", allocationSize = 1, initialValue = 1) // Initial value set to 1
    private Integer id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean status;

    @ManyToOne
    private User assignTo;
}
