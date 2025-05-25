package com.tasksprint.exception;

public record ValidationError(
        String field,     // Campo con error (ej. "nrc")
        String message    // Mensaje de error (ej. "El NRC es obligatorio")
){}