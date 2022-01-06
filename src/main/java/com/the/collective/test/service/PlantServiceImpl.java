package com.the.collective.test.service;

import com.the.collective.test.entities.Plant;
import com.the.collective.test.exception.DataNotFoundException;
import com.the.collective.test.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig(cacheNames = "plantCache")
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepo;

    @Cacheable
    @Override
    public Plant get(long id) {
        return plantRepo.findById(id).orElseThrow(
                () -> new DataNotFoundException("Plant not found"));
    }

    @Cacheable
    @Override
    public Page<Plant> getPlantsByState(String state, Pageable pageable) {
        return plantRepo.findByState(state, pageable);
    }

    @Cacheable
    @Override
    public List<Plant> getPlantsByTopGenerationOutput(Integer size, String orderBy) {
        return plantRepo.findByTopGenerationOutput(size);
    }

    @Cacheable
    @Override
    public List<Plant> getPlantsByBottomGenerationOutput(Integer size, String orderBy) {
        return plantRepo.findByBottomGenerationOutput(size);
    }

    @Cacheable
    @Override
    public List<Object[]> getPercentageForPlantIds(String state, List<Long> plantIds) {
        return plantRepo.getPercentageForPlantIds(state, plantIds);
    }
}