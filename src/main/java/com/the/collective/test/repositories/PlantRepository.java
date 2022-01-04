package com.the.collective.test.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.the.collective.test.entities.Plant;

public interface PlantRepository extends PagingAndSortingRepository<Plant, Long> {
	
	Page<Plant> findByState(String state, Pageable pageable);

}