package com.the.collective.test.entities;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PLANT")
public class Plant {
	
	@Id	
    @SequenceGenerator(name="SEQGEN19", sequenceName="SEQGEN19", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQGEN19")
	@Column(name = "PLANT_ID", nullable = false)
	private Long id;
	
	@Column(name = "YEAR")
	private int year;

	@Column(name = "PSTATABB")
	private String state;
	
	@Column(name = "PNAME")
	private String name;
	
	@Column(name = "GENID")
	private String generatorId;
	
	@Column(name = "GENSTAT")
	private String generatorStatus;
	
	@Column(name = "GENNTAN")
	private String generatorAnnualNetGeneration;

	public Plant() {
	}

	public Plant(Long id, int year, String state, String name, String generatorId, String generatorStatus, String generatorAnnualNetGeneration) {
		this.id = id;
		this.year = year;
		this.state = state;
		this.name = name;
		this.generatorId = generatorId;
		this.generatorStatus = generatorStatus;
		this.generatorAnnualNetGeneration = generatorAnnualNetGeneration;
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

	public String getGeneratorAnnualNetGeneration() {
		return generatorAnnualNetGeneration;
	}

	public void setGeneratorAnnualNetGeneration(String generatorAnnualNetGeneration) {
		this.generatorAnnualNetGeneration = generatorAnnualNetGeneration;
	}
}