package com.tasksprint.exception;

import java.time.LocalDateTime;
import java.util.List;

public record CustomErrorResp(
        LocalDateTime timestamp, // Fecha y hora del error
        String message,          // Mensaje general (ej. "Validación fallida")
        String details,          // Detalles de la solicitud (ej. "uri=/api/classrooms")
        List<ValidationError> errors // Lista de errores por campo
){}
