package com.reca.kruger.persistence.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "rol")
@Data
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRol", nullable = false)
	private Long idRol;

	@Column(name = "tipoRol", nullable = false)
	private String tipoRol;

	@Column(name = "fechaRol", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaRol;

	@OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Persona> personas;

}
