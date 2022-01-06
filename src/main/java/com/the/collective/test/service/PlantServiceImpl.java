package com.the.collective.test.service;

import com.the.collective.test.entities.Plant;
import com.the.collective.test.exception.DataNotFoundException;
import com.the.collective.test.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepo;

    @Override
    public Plant get(long id) {
        return plantRepo.findById(id).orElseThrow(
                () -> new DataNotFoundException("Plant not found"));
    }

    @Override
    public Page<Plant> getPlantsByState(String state, Pageable pageable) {
        Page<Plant> plantEntities = plantRepo.findByState(state, pageable);

        List<Long> plantIds = new ArrayList<>();
        plantEntities.forEach(plant -> plantIds.add(plant.getId()));

        List<Object[]> results = plantRepo.test(state, plantIds);

        Map<Integer, BigDecimal> percentageMap = new HashMap<>();
        results.forEach(percentage -> percentageMap.put((Integer) percentage[0], (BigDecimal) percentage[1]));

        plantEntities.forEach(plant -> plant.setPercentageForLocation(percentageMap.get(plant.getId().intValue())));

        return plantEntities;
    }

    @Override
    public List<Plant> getPlantsByTopGenerationOutput(Integer size, String orderBy) {
        List<Plant> plants = plantRepo.findByTopGenerationOutput(size);

        if("asc".equalsIgnoreCase(orderBy)) {
            List<Plant> plantList = plants.stream()
                    .sorted(Comparator.comparingInt(Plant::getGeneratorAnnualNetGeneration))
                    .collect(Collectors.toList());
            return plantList;
        }

        return plants;
    }

    @Override
    public List<Plant> getPlantsByBottomGenerationOutput(Integer size, String orderBy) {
        List<Plant> plants = plantRepo.findByBottomGenerationOutput(size);

        if("desc".equalsIgnoreCase(orderBy)) {
            List<Plant> plantList = plants.stream()
                    .sorted(Comparator.comparingInt(Plant::getGeneratorAnnualNetGeneration).reversed())
                    .collect(Collectors.toList());
            return plantList;
        }
        return plants;
    }
}