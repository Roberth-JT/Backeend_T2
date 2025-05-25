package com.tasksprint.service.impl;


import com.tasksprint.model.Career;
import com.tasksprint.repo.ICareerRepo;
import com.tasksprint.repo.IGenericRepo;
import com.tasksprint.service.ICareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class CareerService extends GenericService<Career, Integer> implements ICareerService {
    //@Autowired
    private final ICareerRepo repo;

    @Override
    protected IGenericRepo<Career, Integer> getRepo() {
        return repo;
    }
}
