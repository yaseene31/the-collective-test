package com.the.collective.test.service;

import com.the.collective.test.entities.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlantService {
	
	Page<Plant> listAll(Pageable pageable);
     
    Plant get(long id);

    Page<Plant> getPlantsByState(String state, Pageable pageable);
}