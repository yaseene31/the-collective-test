package com.the.collective.test.service;

import com.the.collective.test.entities.Plant;
import com.the.collective.test.exception.DataNotFoundException;
import com.the.collective.test.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepo;

    @Override
    public Page<Plant> listAll(Pageable pageable) {
        return plantRepo.findAll(pageable);
    }

    @Override
    public Plant get(long id) {
        return plantRepo.findById(id).orElseThrow(
                () -> new DataNotFoundException("Plant not found"));
    }

    @Override
    public Page<Plant> getPlantsByState(String state, Pageable pageable) {
        return plantRepo.findByState(state, pageable);
    }
}