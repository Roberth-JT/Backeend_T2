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
public class Workgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idWorkgroup;

    @Column(nullable = false)
    private String workgroupNumber;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String projectDescription;

    private String ciaName; // empresa donde se elabora el proyecto

    private String projectDetails; // detalles adicionales

    @ManyToOne //FK
    @JoinColumn(name = "id_classroom", nullable = false, foreignKey = @ForeignKey(name = "FK_WORKGROUP_CLASSROOM"))
    private Classroom classroom;

    @ManyToOne //FK
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_WORKGROUP_USER"))
    private User user;
}
