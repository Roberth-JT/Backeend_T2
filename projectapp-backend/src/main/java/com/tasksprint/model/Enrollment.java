package com.tasksprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEnrollment;

    @Column(nullable = true, length = 5, precision = 2)
    private BigDecimal score;

    private String comments;

    @ManyToOne //FK
    @JoinColumn(name = "id_workgroup", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLLMENT_WORKGROUP"))
    private Workgroup workgroup;

    @ManyToOne //FK
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLLMENT_STUDENT"))
    private Student student;
}
