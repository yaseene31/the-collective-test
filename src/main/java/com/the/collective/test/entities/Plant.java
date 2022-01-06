package com.the.collective.test.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PLANT")
public class Plant {
	
	@Id	
    @SequenceGenerator(name="SEQGEN19", sequenceName="SEQGEN19", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQGEN19")
	@Column(name = "PLANT_ID", nullable = false)
	private Long id;
	
	@Column(name = "YEAR")
	private Integer year;

	@Column(name = "PSTATABB")
	private String state;
	
	@Column(name = "PNAME")
	private String name;
	
	@Column(name = "GENID")
	private String generatorId;
	
	@Column(name = "GENSTAT")
	private String generatorStatus;
	
	@Column(name = "GENNTAN")
	private Integer generatorAnnualNetGeneration;

	@Transient
	private BigDecimal percentageForLocation;

	public Plant() {
	}

	public Plant(Long id, Integer year, String state, String name, String generatorId, String generatorStatus, Integer generatorAnnualNetGeneration, BigDecimal percentageForLocation) {
		this.id = id;
		this.year = year;
		this.state = state;
		this.name = name;
		this.generatorId = generatorId;
		this.generatorStatus = generatorStatus;
		this.generatorAnnualNetGeneration = generatorAnnualNetGeneration;
		this.percentageForLocation = percentageForLocation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}

	public String getGeneratorStatus() {
		return generatorStatus;
	}

	public void setGeneratorStatus(String generatorStatus) {
		this.generatorStatus = generatorStatus;
	}

	public Integer getGeneratorAnnualNetGeneration() {
		return generatorAnnualNetGeneration;
	}

	public void setGeneratorAnnualNetGeneration(Integer generatorAnnualNetGeneration) {
		this.generatorAnnualNetGeneration = generatorAnnualNetGeneration;
	}

	public BigDecimal getPercentageForLocation() {
		return percentageForLocation;
	}

	public void setPercentageForLocation(BigDecimal percentageForLocation) {
		this.percentageForLocation = percentageForLocation;
	}
}