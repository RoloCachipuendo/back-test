package com.reca.kruger.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "vacuna")
@Data
public class Vacuna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacuna;

	@Column(name = "tipoVacuna", nullable = false)
	private String tipoVacuna;

	@Column(name = "fechaVacuna", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaVacuna;

	@Column(name = "numDosisVacuna", nullable = false)
	private Integer numDosisVacuna;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPersona", nullable = false)
	@JsonIgnore
	private Persona persona;

}
