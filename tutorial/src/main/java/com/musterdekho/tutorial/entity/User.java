package com.musterdekho.tutorial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence_name", allocationSize = 1, initialValue = 1001)
    private Integer id; // Change the data type according to your needs

    private String name;
    private String userName;
    private String password;

}
