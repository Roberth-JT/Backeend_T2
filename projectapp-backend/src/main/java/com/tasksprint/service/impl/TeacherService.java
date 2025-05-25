package com.tasksprint.service.impl;

import com.tasksprint.model.Teacher;
import com.tasksprint.repo.IGenericRepo;
import com.tasksprint.repo.ITeacherRepo;
import com.tasksprint.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherService extends GenericService<Teacher, Integer> implements ITeacherService {
    //@Autowired
    private final ITeacherRepo repo;

    @Override
    protected IGenericRepo<Teacher, Integer> getRepo() {
        return repo;
    }
}
