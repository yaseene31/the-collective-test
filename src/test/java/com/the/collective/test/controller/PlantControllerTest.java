package com.the.collective.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the.collective.test.dto.PlantDto;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.exception.DataNotFoundException;
import com.the.collective.test.service.PlantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlantControllerTest {

    @Autowired
    PlantController plantController;

    @MockBean
    PlantService plantService;

    @Test
    void when_IdIsFound_Expect_PlantDto() throws IOException {
        Mockito.when(plantService.get(10)).thenReturn(getPlantById());

        ResponseEntity<PlantDto> plantDtoResponseEntity = plantController.getById(10);
        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());

        PlantDto plantDto = plantDtoResponseEntity.getBody();
        assertNotNull(plantDto.getId());
        assertNotNull(plantDto.getYear());
        assertNotNull(plantDto.getName());
        assertNotNull(plantDto.getGeneratorId());
        assertNotNull(plantDto.getGeneratorStatus());
        assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
    }

    @Test
    void should_ThrowException_When_IdNotFound() {
        Mockito.when(plantService.get(10)).thenThrow(new DataNotFoundException("Plant not found"));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> {
            plantController.getById(10);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("Plant not found"));
    }

    @Test
    void when_stateIsFound_Expect_PlantDtoList() throws IOException {
        Mockito.when(plantService.getPlantsByState(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(getPlantByState());
        Mockito.when(plantService.getPercentageForPlantIds(Mockito.anyString(), Mockito.any(List.class))).thenReturn(getPercentageForPlantIds());

        ResponseEntity<PagedModel<PlantDto>> plantDtoResponseEntity = plantController.getPlantsByState(PageRequest.of(1,10), "AK");

        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());
        plantDtoResponseEntity.getBody().getContent().forEach(plantDto -> {
            assertNotNull(plantDto.getId());
            assertNotNull(plantDto.getYear());
            assertNotNull(plantDto.getName());
            assertNotNull(plantDto.getGeneratorId());
            assertNotNull(plantDto.getGeneratorStatus());
            assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
            assertNotNull(plantDto.getPercentageForLocation());
        });
    }

    @Test
    void when_TopGenerationOutputIsRequestAsc_Expect_PlantDtoList() throws IOException {
        Mockito.when(plantService.getPlantsByTopGenerationOutput(Mockito.anyInt(), Mockito.anyString())).thenReturn(getPlantByTopGenerationOutput());

        ResponseEntity<List<PlantDto>> plantDtoResponseEntity = plantController.getPlantsByTopGenerationOutput(10, "asc");

        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());

        List<PlantDto> body = plantDtoResponseEntity.getBody();

        assertTrue(body.get(0).getId().equals(new Long(214)));

        body.forEach(plantDto -> {
            assertNotNull(plantDto.getId());
            assertNotNull(plantDto.getYear());
            assertNotNull(plantDto.getName());
            assertNotNull(plantDto.getGeneratorId());
            assertNotNull(plantDto.getGeneratorStatus());
            assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
        });
    }

    @Test
    void when_TopGenerationOutputIsRequestDesc_Expect_PlantDtoList() throws IOException {
        Mockito.when(plantService.getPlantsByTopGenerationOutput(Mockito.anyInt(), Mockito.anyString())).thenReturn(getPlantByTopGenerationOutput());

        ResponseEntity<List<PlantDto>> plantDtoResponseEntity = plantController.getPlantsByTopGenerationOutput(10, "desc");

        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());

        List<PlantDto> body = plantDtoResponseEntity.getBody();

        assertTrue(body.get(0).getId().equals(new Long(448)));

        body.forEach(plantDto -> {
            assertNotNull(plantDto.getId());
            assertNotNull(plantDto.getYear());
            assertNotNull(plantDto.getName());
            assertNotNull(plantDto.getGeneratorId());
            assertNotNull(plantDto.getGeneratorStatus());
            assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
        });
    }

    @Test
    void when_BottomGenerationOutputIsRequestDesc_Expect_PlantDtoList() throws IOException {
        Mockito.when(plantService.getPlantsByBottomGenerationOutput(Mockito.anyInt(), Mockito.anyString())).thenReturn(getPlantByBottomGenerationOutput());

        ResponseEntity<List<PlantDto>> plantDtoResponseEntity = plantController.getPlantsByBottomGenerationOutput(10, "desc");

        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());

        List<PlantDto> body = plantDtoResponseEntity.getBody();

        assertTrue(body.get(0).getId().equals(new Long(224)));

        body.forEach(plantDto -> {
            assertNotNull(plantDto.getId());
            assertNotNull(plantDto.getYear());
            assertNotNull(plantDto.getName());
            assertNotNull(plantDto.getGeneratorId());
            assertNotNull(plantDto.getGeneratorStatus());
            assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
        });
    }

    @Test
    void when_BottomGenerationOutputIsRequestAsc_Expect_PlantDtoList() throws IOException {
        Mockito.when(plantService.getPlantsByBottomGenerationOutput(Mockito.anyInt(), Mockito.anyString())).thenReturn(getPlantByBottomGenerationOutput());

        ResponseEntity<List<PlantDto>> plantDtoResponseEntity = plantController.getPlantsByBottomGenerationOutput(10, "asc");

        assertTrue(plantDtoResponseEntity.getStatusCode().is2xxSuccessful());

        List<PlantDto> body = plantDtoResponseEntity.getBody();

        assertTrue(body.get(0).getId().equals(new Long(247)));

        body.forEach(plantDto -> {
            assertNotNull(plantDto.getId());
            assertNotNull(plantDto.getYear());
            assertNotNull(plantDto.getName());
            assertNotNull(plantDto.getGeneratorId());
            assertNotNull(plantDto.getGeneratorStatus());
            assertNotNull(plantDto.getGeneratorAnnualNetGeneration());
        });
    }

    private Plant getPlantById() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/plant-by-id.json").getFile();
        Plant plant = objectMapper.readValue(
                resource, Plant.class);
        return plant;
    }

    private Page<Plant> getPlantByState() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/plant-by-state.json").getFile();
        Plant[] plants = objectMapper.readValue(
                resource, Plant[].class);
        Page<Plant> pagedResponse = new PageImpl(Arrays.asList(plants));
        return pagedResponse;
    }

    private List<Object[]> getPercentageForPlantIds() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/plant-by-state.json").getFile();
        Plant[] plants = objectMapper.readValue(
                resource, Plant[].class);

        List<Object[]> list = new ArrayList<>();
        Arrays.asList(plants).forEach(plant -> list.add(new Object[]{plant.getId().intValue(), new BigDecimal(2)}));

        return list;
    }

    private List<Plant> getPlantByTopGenerationOutput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/plant-by-top-generation-output.json").getFile();
        Plant[] plants = objectMapper.readValue(
                resource, Plant[].class);
        return Arrays.asList(plants);
    }

    private List<Plant> getPlantByBottomGenerationOutput() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File resource = new ClassPathResource(
                "data/plant-by-bottom-generation-output.json").getFile();
        Plant[] plants = objectMapper.readValue(
                resource, Plant[].class);
        return Arrays.asList(plants);
    }
}
