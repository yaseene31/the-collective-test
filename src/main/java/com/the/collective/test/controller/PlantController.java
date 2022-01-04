package com.the.collective.test.controller;

import com.the.collective.test.assemblers.PlantAssembler;
import com.the.collective.test.domain.PlantService;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.resources.PlantResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plant/v1")
public class PlantController {

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    PlantService plantService;

    @Autowired
    private PagedResourcesAssembler<Plant> pagedResourcesAssembler;

    @Autowired
    private PlantAssembler plantAssembler;

    @GetMapping("/all")
    public ResponseEntity<PagedModel<PlantResource>> getAllPlants(Pageable pageable) {

        Page<Plant> plantEntities = plantService.listAll(pageable);
        PagedModel<PlantResource> plantCollection = pagedResourcesAssembler
                .toModel(plantEntities, plantAssembler);

        return new ResponseEntity<>(plantCollection, HttpStatus.OK);
    }
}