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
@Table(name = "clave")
@Data
public class Clave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClave;

	@Column(name = "fechaClave", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaClave;

	@Column(name = "descripcionClave", nullable = false)
	private String descripcionClave;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPersona", nullable = false)
	@JsonIgnore
	private Persona persona;

}
