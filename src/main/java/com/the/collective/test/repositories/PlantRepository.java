package com.the.collective.test.repositories;

import com.the.collective.test.entities.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantRepository extends PagingAndSortingRepository<Plant, Long> {

	Page<Plant> findByState(String state, Pageable pageable);

	@Query(value = "SELECT * FROM PLANT order by genntan desc limit :size", nativeQuery = true)
	List<Plant> findByTopGenerationOutput(@Param("size") Integer size);

	@Query(nativeQuery = true, value = "SELECT * FROM PLANT order by genntan asc limit :size")
	List<Plant> findByBottomGenerationOutput(@Param("size") Integer size);

	@Query(value = "SELECT plant_id,ROUND(genntan * 100.0/ (SELECT sum(genntan) FROM PLANT where pstatabb = :state), 2) as percentage FROM PLANT where plant_id in (:plantIds)" +
			"", nativeQuery = true)
	List<Object[]> getPercentageForPlantIds(@Param("state") String state, @Param("plantIds") List<Long> plantIds);
}