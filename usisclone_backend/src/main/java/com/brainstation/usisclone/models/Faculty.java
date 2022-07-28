package com.brainstation.usisclone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name="tbl_faculty",
        uniqueConstraints = @UniqueConstraint(
                name = "faculty_email_unique",
                columnNames = "faculty_email"
        )
)

public class Faculty {

    @Id
    @SequenceGenerator(
            name="faculty_sequence",
            allocationSize = 1,
            sequenceName = "faculty_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_sequence"
    )

    private Long faculty_id;
    private String faculty_name;

    @Column(name = "faculty_email",
            nullable = false)
    private String faculty_email;
    private String faculty_mobileNumber;
}
