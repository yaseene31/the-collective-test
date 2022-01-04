package com.the.collective.test.domain;

import com.the.collective.test.repositories.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.the.collective.test.entities.Plant;

@Service
public class PlantServiceImpl implements PlantService {

    private static final Logger logger = LoggerFactory.getLogger(PlantServiceImpl.class);

    @Autowired
    PlantRepository plantRepo;

    @Override
    public Page<Plant> listAll(Pageable pageable) {
        return plantRepo.findAll(pageable);
    }

//    @Override
//    public Plant get(long id) throws ResourceNotFoundException {
//        try {
//            return plantRepo.findById(id).orElseThrow(
//                    () -> new ResourceNotFoundException("Service Provider not found for this id :: " + id));
//        } catch (ResourceNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            logger.error("GET failed for Service Provider with ID({}). Reason({})", id, e.toString());
//            throw new ServiceProviderException("Failed to get  Service Provider", e);
//        }
//    }
}