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

@Data
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
}