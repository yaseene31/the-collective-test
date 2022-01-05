package com.the.collective.test.controller;

import com.the.collective.test.assemblers.PlantAssembler;
import com.the.collective.test.service.PlantService;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.resources.PlantDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class PlantController {

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    PlantService plantService;

    @Autowired
    private PagedResourcesAssembler<Plant> pagedResourcesAssembler;

    @Autowired
    private PlantAssembler plantAssembler;

    @GetMapping("/plants/{id}")
    public ResponseEntity<PlantDto> getById(Pageable pageable, @PathVariable(value = "id") long id) {

        Plant plant = plantService.get(id);
        PlantDto plantDto = plantAssembler.toModel(plant);

        return new ResponseEntity<>(plantDto, HttpStatus.OK);
    }

    @GetMapping("/plants/state/{state}")
    public ResponseEntity<PagedModel<PlantDto>> getPlantsByState(Pageable pageable,
                                                                 @PathVariable(value = "state") String state) {

        Page<Plant> plantEntities = plantService.getPlantsByState(state, pageable);
        PagedModel<PlantDto> plantCollection = pagedResourcesAssembler
                .toModel(plantEntities, plantAssembler);

        return new ResponseEntity<>(plantCollection, HttpStatus.OK);
    }
}