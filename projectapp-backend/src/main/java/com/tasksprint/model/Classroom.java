package com.tasksprint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idClassroom;

    @Column(nullable = false)
    private Integer nrc;

    @ManyToOne //FK
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_CLASSROOM_COURSE"))
    private Course course;

    @ManyToOne //FK
    @JoinColumn(name = "id_teacher", nullable = false, foreignKey = @ForeignKey(name = "FK_CLASSROOM_TEACHER"))
    private Teacher teacher;

    @ManyToOne //FK
    @JoinColumn(name = "id_career", nullable = false, foreignKey = @ForeignKey(name = "FK_CLASSROOM_CAREER"))
    private Career career;

    @Column(nullable = false, length = 10)
    private String semester; // 2025-1, 2024-2

    @Column(nullable = false)
    private Integer level; // ciclo: 1, 2, 3

    @Column(nullable = false)
    private Boolean status; // 1 - En desarrollo, 0 - Terminado
}
