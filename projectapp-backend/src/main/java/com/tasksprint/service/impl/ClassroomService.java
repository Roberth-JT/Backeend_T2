package com.tasksprint.service.impl;

import com.tasksprint.exception.ModelNotFoundException;
import com.tasksprint.model.Career;
import com.tasksprint.model.Classroom;
import com.tasksprint.model.Course;
import com.tasksprint.model.Teacher;
import com.tasksprint.repo.ICareerRepo;
import com.tasksprint.repo.IClassroomRepo;
import com.tasksprint.repo.ICourseRepo;
import com.tasksprint.repo.ITeacherRepo;
import com.tasksprint.repo.IGenericRepo;
import com.tasksprint.service.IClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassroomService extends GenericService<Classroom, Integer> implements IClassroomService {

    private final IClassroomRepo repo;
    private final ICourseRepo courseRepo;
    private final ITeacherRepo teacherRepo;
    private final ICareerRepo careerRepo;

    @Override
    protected IGenericRepo<Classroom, Integer> getRepo() {
        return repo;
    }

    @Override
    public Classroom save(Classroom entity) throws Exception {
        validarEntidadesRelacionadas(entity);
        return super.save(entity);
    }

    @Override
    public Classroom update(Classroom entity, Integer id) throws Exception {
        Classroom existente = findById(id);
        validarEntidadesRelacionadas(entity);

        existente.setNrc(entity.getNrc());
        existente.setCourse(entity.getCourse());
        existente.setTeacher(entity.getTeacher());
        existente.setCareer(entity.getCareer());
        existente.setSemester(entity.getSemester());
        existente.setLevel(entity.getLevel());
        existente.setStatus(entity.getStatus());

        return repo.save(existente);
    }

    private void validarEntidadesRelacionadas(Classroom classroom) throws ModelNotFoundException {
        Optional<Course> curso = courseRepo.findById(classroom.getCourse().getIdCourse());
        if (curso.isEmpty()) {
            throw new ModelNotFoundException("Curso con ID " + classroom.getCourse().getIdCourse() + " no encontrado.");
        }

        Optional<Teacher> docente = teacherRepo.findById(classroom.getTeacher().getIdTeacher());
        if (docente.isEmpty()) {
            throw new ModelNotFoundException("Docente con ID " + classroom.getTeacher().getIdTeacher() + " no encontrado.");
        }

        Optional<Career> carrera = careerRepo.findById(classroom.getCareer().getIdCareer());
        if (carrera.isEmpty()) {
            throw new ModelNotFoundException("Carrera con ID " + classroom.getCareer().getIdCareer() + " no encontrada.");
        }
    }
}
