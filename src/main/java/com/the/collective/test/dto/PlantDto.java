package com.the.collective.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PlantDto extends RepresentationModel<PlantDto>{

	private Long id;

	private int year;

	private String state;

	private String name;

	private String generatorId;

	private String generatorStatus;

	private Integer generatorAnnualNetGeneration;

	private BigDecimal percentageForLocation;
}