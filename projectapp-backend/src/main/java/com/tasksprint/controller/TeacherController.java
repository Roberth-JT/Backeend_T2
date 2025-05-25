package com.tasksprint.controller;

import com.tasksprint.dto.TeacherDTO;
import com.tasksprint.model.Teacher;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeacherController {
    private final ITeacherService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAll() throws  Exception{
        List<TeacherDTO> list = service.findAll().stream().map(this::converToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable("id") Integer id) throws Exception{
        TeacherDTO obj =  converToDto(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TeacherDTO dto) throws Exception{
        Teacher obj =  service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTeacher()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TeacherDTO dto) throws Exception{
        Teacher obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TeacherDTO converToDto(Teacher obj){
        return modelMapper.map(obj, TeacherDTO.class);
    }

    private Teacher convertToEntity(TeacherDTO dto){
        return modelMapper.map(dto, Teacher.class);
    }
}
