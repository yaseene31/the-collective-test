package com.the.collective.test.controller;

import com.the.collective.test.assemblers.PlantAssembler;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.dto.PlantDto;
import com.the.collective.test.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Operation(
            summary = "Finds a plant",
            description = "Finds a plant by their Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlantDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            }
    )
    public ResponseEntity<PlantDto> getById(@Parameter(description = "The Id of the plant to find.") @PathVariable(value = "id") long id) {

        Plant plant = plantService.get(id);
        PlantDto plantDto = plantAssembler.toModel(plant);

        return new ResponseEntity<>(plantDto, HttpStatus.OK);
    }

    @GetMapping("/plants/state/{state}")
    @Operation(
            summary = "Finds a list of plants based on the state",
            description = "Finds a list of plants by their state location.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = PlantDto.class)))
                    ),
            }
    )
    public ResponseEntity<PagedModel<PlantDto>> getPlantsByState(Pageable pageable,
                                                                 @Parameter(description = "The state of where the plant is located")
                                                                 @PathVariable(value = "state") String state) {

        Page<Plant> plantEntities = plantService.getPlantsByState(state, pageable);

        Map<Integer, BigDecimal> percentageMap = getPercentageMap(plantEntities, state);

        plantEntities.forEach(plant -> plant.setPercentageForLocation(percentageMap.get(plant.getId().intValue())));

        PagedModel<PlantDto> plantCollection = pagedResourcesAssembler
                .toModel(plantEntities, plantAssembler);

        return new ResponseEntity<>(plantCollection, HttpStatus.OK);
    }

    @GetMapping("/plants/top")
    @Operation(
            summary = "Finds a list of the top plants",
            description = "Finds a list of top plants by generation output",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = PlantDto.class)))
                    ),
            }
    )
    public ResponseEntity<List<PlantDto>> getPlantsByTopGenerationOutput(
            @Parameter(description = "The number of top plants")  @RequestParam(name = "size") Integer size,
            @Parameter(description = "Order by asc or desc") @RequestParam(name = "orderBy") String orderBy) {

        List<Plant> plantEntities = plantService.getPlantsByTopGenerationOutput(size, orderBy);

        if("asc".equalsIgnoreCase(orderBy)) {
            plantEntities = plantEntities.stream()
                    .sorted(Comparator.comparingInt(Plant::getGeneratorAnnualNetGeneration))
                    .collect(Collectors.toList());
        }

        List<PlantDto> plantDtos = plantAssembler.listToModel(plantEntities);

        return new ResponseEntity<>(plantDtos, HttpStatus.OK);
    }

    @GetMapping("/plants/bottom")
    @Operation(
            summary = "Finds a list of the bottom plants",
            description = "Finds a list of bottom plants by generation output",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema =  @Schema(implementation = PlantDto.class)))
                    ),
            }
    )
    public ResponseEntity<List<PlantDto>> getPlantsByBottomGenerationOutput(
            @Parameter(description = "The number of bottom plants")  @RequestParam(name = "size") Integer size,
            @Parameter(description = "Order by asc or desc") @RequestParam(name = "orderBy") String orderBy) {

        List<Plant> plantEntities = plantService.getPlantsByBottomGenerationOutput(size, orderBy);

        if("desc".equalsIgnoreCase(orderBy)) {
            plantEntities = plantEntities.stream()
                    .sorted(Comparator.comparingInt(Plant::getGeneratorAnnualNetGeneration).reversed())
                    .collect(Collectors.toList());
        }

        List<PlantDto> plantDtos = plantAssembler.listToModel(plantEntities);

        return new ResponseEntity<>(plantDtos, HttpStatus.OK);
    }

    private Map<Integer, BigDecimal> getPercentageMap(Page<Plant> plantEntities, String state) {
        List<Long> plantIds = new ArrayList<>();
        plantEntities.forEach(plant -> plantIds.add(plant.getId()));

        List<Object[]> results = plantService.getPercentageForPlantIds(state, plantIds);

        Map<Integer, BigDecimal> percentageMap = new HashMap<>();
        results.forEach(percentage -> percentageMap.put((Integer) percentage[0], (BigDecimal) percentage[1]));
        return percentageMap;
    }
}