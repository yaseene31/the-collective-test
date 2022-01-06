package com.the.collective.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the.collective.test.entities.Plant;
import com.the.collective.test.exception.DataNotFoundException;
import com.the.collective.test.resources.PlantDto;
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
import java.util.Arrays;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlantControllerTest {

    @Autowired
    PlantController plantController;

    @MockBean
    PlantService plantService;

    @Test
        void contextLoads() {
    }

    @Test
    void testGetPlanById() throws IOException {
        Mockito.when(plantService.get(10)).thenReturn(getPlantById());

        ResponseEntity<PlantDto> plantDto = plantController.getById(10);
        assertTrue(plantDto.getStatusCode().is2xxSuccessful());

    }

    @Test
    void testNegativeGetPlanById() {
        Mockito.when(plantService.get(10)).thenThrow(new DataNotFoundException("Plant not found"));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> {
            plantController.getById(10);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("Plant not found"));
    }

    @Test
    void testGetPlanByState() throws IOException {

        Mockito.when(plantService.getPlantsByState(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(getPlantByState());

        ResponseEntity<PagedModel<PlantDto>> plantDto = plantController.getPlantsByState(PageRequest.of(1,10), "AK");

        assertTrue(plantDto.getStatusCode().is2xxSuccessful());
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

}
