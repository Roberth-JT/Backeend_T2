package com.tasksprint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerDTO {
    private Integer idCareer;
    private String code;
    private String name;
}
