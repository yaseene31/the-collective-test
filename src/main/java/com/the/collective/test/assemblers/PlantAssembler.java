package com.the.collective.test.assemblers;

import com.the.collective.test.controller.PlantController;
import com.the.collective.test.service.PlantService;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.resources.PlantDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlantAssembler extends RepresentationModelAssemblerSupport<Plant, PlantDto> {

    @Autowired
    PlantService plantService;

    private static final Logger logger = LoggerFactory.getLogger(PlantAssembler.class);

    public PlantAssembler() {
        super(PlantController.class, PlantDto.class);
    }

    @Override
    public PlantDto toModel(Plant entity) {
        PlantDto plantDto = instantiateModel(entity);

        plantDto.setId(entity.getId());
        plantDto.setYear(entity.getYear());
        plantDto.setState(entity.getState());
        plantDto.setName(entity.getName());
        plantDto.setGeneratorId(entity.getGeneratorId());
        plantDto.setGeneratorStatus(entity.getGeneratorStatus());
        plantDto.setGeneratorAnnualNetGeneration(entity.getGeneratorAnnualNetGeneration());

        return plantDto;
    }
}