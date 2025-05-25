package com.tasksprint.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDTO {
    private Integer idClassroom;

    @NotNull(message = "El NRC es obligatorio")
    @Positive(message = "El NRC debe ser un número entero positivo")
    private Integer nrc;

    @NotNull(message = "El ID del Curso es obligatorio")
    private Integer idCourse;

    @NotNull(message = "El ID del Docente es obligatorio")
    private Integer idTeacher;

    @NotNull(message = "El ID de la Carrera es obligatorio")
    private Integer idCareer;

    @NotBlank(message = "El semestre es obligatorio")
    @Size(max = 10, message = "El semestre debe tener como máximo 10 caracteres")
    private String semester;

    @NotNull(message = "El nivel es obligatorio")
    @Min(value = 1, message = "El nivel debe ser al menos 1")
    @Max(value = 10, message = "El nivel debe ser como máximo 10")
    private Integer level;

    @NotNull(message = "El estado es obligatorio")
    private Boolean status;
}

