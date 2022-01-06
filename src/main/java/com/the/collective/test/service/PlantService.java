package com.the.collective.test.service;

import com.the.collective.test.entities.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlantService {
     
    Plant get(long id);

    Page<Plant> getPlantsByState(String state, Pageable pageable);

    List<Plant> getPlantsByTopGenerationOutput(Integer size, String orderBy);

    List<Plant> getPlantsByBottomGenerationOutput(Integer size, String orderBy);

    List<Object[]> getPercentageForPlantIds(String state, List<Long> plantIds);
}