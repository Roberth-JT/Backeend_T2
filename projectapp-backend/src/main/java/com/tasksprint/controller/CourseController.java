package com.tasksprint.controller;

import com.tasksprint.dto.CourseDTO;
import com.tasksprint.model.Course;
import com.tasksprint.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {
    private final ICourseService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() throws  Exception{
        List<CourseDTO> list = service.findAll().stream().map(this::converToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") Integer id) throws Exception{
        CourseDTO obj =  converToDto(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj =  service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCourse()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CourseDTO converToDto(Course obj){
        return modelMapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return modelMapper.map(dto, Course.class);
    }
}
