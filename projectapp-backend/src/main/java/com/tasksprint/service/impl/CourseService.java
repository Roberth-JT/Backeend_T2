package com.tasksprint.service.impl;

import com.tasksprint.model.Course;
import com.tasksprint.repo.ICourseRepo;
import com.tasksprint.repo.IGenericRepo;
import com.tasksprint.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class CourseService extends GenericService<Course, Integer> implements ICourseService {
    //@Autowired
    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
