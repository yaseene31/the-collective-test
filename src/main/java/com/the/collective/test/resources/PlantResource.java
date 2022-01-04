package com.the.collective.test.resources;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class PlantResource extends RepresentationModel<PlantResource>{

	private Long id;

	private int year;

	private String state;

	private String name;

	private String generatorId;

	private String generatorStatus;

	private String generatorAnnualNetGeneration;
}