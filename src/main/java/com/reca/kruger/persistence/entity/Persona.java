package com.reca.kruger.persistence.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPersona")
	private Long idPersona;

	@Column(name = "cedulaPersona", nullable = false, length = 10)
	private String cedulaPersona;

	@Column(name = "nombresPersona", nullable = false)
	private String nombresPersona;

	@Column(name = "apellidosPersona", nullable = false)
	private String apellidosPersona;

	@Column(name = "correoPersona", nullable = false, unique = true)
	private String correoPersona;

	@Column(name = "fechaNacimientoPersona", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimientoPersona;

	@Column(name = "direccionDomicilioPersona", nullable = true)
	private String direccionDomicilioPersona;

	@Column(name = "celularPersona", nullable = true)
	private String celularPersona;

	@Column(name = "estadoVacunacionPersona", nullable = true)
	private String estadoVacunacionPersona;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRol", nullable = false)
	@JsonIgnore
	private Rol rol;

	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vacuna> vacunas;

	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Clave> claves;

}
