package com.tasksprint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Integer idTeacher;
    public String code;
    public String motherLastname;
    public String fatherLastname;
    public String firstName;
    public String secondName;
}
