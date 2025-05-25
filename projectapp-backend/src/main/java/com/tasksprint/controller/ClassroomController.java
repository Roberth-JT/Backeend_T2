package com.tasksprint.controller;

import com.tasksprint.dto.ClassroomDTO;
import com.tasksprint.model.Career;
import com.tasksprint.model.Classroom;
import com.tasksprint.model.Course;
import com.tasksprint.model.Teacher;
import com.tasksprint.service.ICareerService;
import com.tasksprint.service.IClassroomService;
import com.tasksprint.service.ICourseService;
import com.tasksprint.service.ITeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClassroomController {

    private final IClassroomService service;
    private final ICourseService courseService;
    private final ITeacherService teacherService;
    private final ICareerService careerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> findAll() throws Exception {
        List<ClassroomDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Classroom obj = service.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClassroomDTO dto) throws Exception {
        Classroom obj = convertToEntity(dto);
        obj = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdClassroom()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ClassroomDTO dto) throws Exception {
        Classroom obj = convertToEntity(dto);
        obj = service.update(obj, id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ClassroomDTO convertToDto(Classroom classroom) {
        ClassroomDTO dto = modelMapper.map(classroom, ClassroomDTO.class);

        // Extraemos solo los IDs de las entidades relacionadas para el DTO
        dto.setIdCourse(classroom.getCourse().getIdCourse());
        dto.setIdTeacher(classroom.getTeacher().getIdTeacher());
        dto.setIdCareer(classroom.getCareer().getIdCareer());

        return dto;
    }

    private Classroom convertToEntity(ClassroomDTO dto) throws Exception {
        Classroom classroom = modelMapper.map(dto, Classroom.class);

        // Buscar las entidades relacionadas por sus IDs usando los servicios
        Course course = courseService.findById(dto.getIdCourse());
        Teacher teacher = teacherService.findById(dto.getIdTeacher());
        Career career = careerService.findById(dto.getIdCareer());

        // Asignar las entidades a classroom
        classroom.setCourse(course);
        classroom.setTeacher(teacher);
        classroom.setCareer(career);

        return classroom;
    }
}
