package com.brainstation.usisclone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Course {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            allocationSize = 1,
            sequenceName = "student_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long course_id;
    private String course_name;

    private String course_description;
}
