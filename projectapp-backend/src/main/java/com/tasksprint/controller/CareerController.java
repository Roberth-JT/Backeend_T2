package com.tasksprint.controller;

import com.tasksprint.dto.CareerDTO;
import com.tasksprint.model.Career;
import com.tasksprint.service.ICareerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/careers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CareerController {
    private final ICareerService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CareerDTO>> findAll() throws  Exception{
        List<CareerDTO> list = service.findAll().stream().map(this::converToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareerDTO> findById(@PathVariable("id") Integer id) throws Exception{
        CareerDTO obj =  converToDto(service.findById(id));
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CareerDTO dto) throws Exception{
        Career obj =  service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCareer()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CareerDTO dto) throws Exception{
        Career obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CareerDTO converToDto(Career obj){
        return modelMapper.map(obj, CareerDTO.class);
    }

    private Career convertToEntity(CareerDTO dto){
        return modelMapper.map(dto, Career.class);
    }
}