package com.the.collective.test.assemblers;

import com.the.collective.test.controller.PlantController;
import com.the.collective.test.domain.PlantService;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.resources.PlantResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlantAssembler extends RepresentationModelAssemblerSupport<Plant, PlantResource> {

    @Autowired
    PlantService plantService;

    private static final Logger logger = LoggerFactory.getLogger(PlantAssembler.class);

    public PlantAssembler() {
        super(PlantController.class, PlantResource.class);
    }

    @Override
    public PlantResource toModel(Plant entity) {
        PlantResource plantResource = instantiateModel(entity);

        plantResource.setId(entity.getId());
        plantResource.setYear(entity.getYear());
        plantResource.setState(entity.getState());
        plantResource.setName(entity.getName());
        plantResource.setGeneratorId(entity.getGeneratorId());
        plantResource.setGeneratorStatus(entity.getGeneratorStatus());
        plantResource.setGeneratorAnnualNetGeneration(entity.getGeneratorAnnualNetGeneration());

        return plantResource;
    }

    @Override
    public CollectionModel<PlantResource> toCollectionModel(Iterable<? extends Plant> entities) {
        CollectionModel<PlantResource> plantModels = super.toCollectionModel(entities);

        plantModels.add(linkTo(methodOn(PlantController.class).getAllPlants(null)).withSelfRel());

        return plantModels;
    }
}