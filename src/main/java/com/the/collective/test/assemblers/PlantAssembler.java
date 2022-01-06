package com.the.collective.test.assemblers;

import com.the.collective.test.controller.PlantController;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.dto.PlantDto;
import com.the.collective.test.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlantAssembler extends RepresentationModelAssemblerSupport<Plant, PlantDto> {

    @Autowired
    PlantService plantService;

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
        plantDto.setPercentageForLocation(entity.getPercentageForLocation());

        return plantDto;
    }

    public List<PlantDto> listToModel(List<Plant> entities) {
        List<PlantDto> plantDtos = new ArrayList<>();
        entities.forEach(plant -> plantDtos.add(
                toModel(plant)));
        return plantDtos;
    }
}