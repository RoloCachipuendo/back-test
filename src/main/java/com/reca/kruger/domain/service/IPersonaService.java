package com.reca.kruger.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reca.kruger.domain.dto.PersonaDto;
import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Vacuna;

public interface IPersonaService {

	/**
	 * Metodo par aobtener Empleados por eatdo de vacunación
	 * 
	 * @param estadoVacuna estado de la vacuna (true-> vacunado; false-> no
	 *                     vacunado)
	 * @return List<{@link Persona}>
	 */
	public List<Persona> obtenerPersonasPorEstadoVacuna(Boolean estadoVacuna);

	/**
	 * Método para obtener Empleados por el Tipo de Vacuna
	 * 
	 * @param tipoVacuna Tipo de Vacuna Ej. Sputnik, AstraZeneca
	 * @return List<{@link Persona}>
	 */
	public List<Persona> obtenerPersonasPorTipoVacuna(String tipoVacuna);

	/**
	 * Método para obtener Empleados por Fecha de Vacunación
	 * 
	 * @param fechaVacuna la fecha de vacunación
	 * @return List<{@link Persona}>
	 */
	public List<Persona> obtenerPersonasPorFechaVacuna(Date fechaInicial, Date fechaFinal);

	/**
	 * Método para Guardar el empleado
	 * 
	 * @param empleado objeto Persoan
	 * @return {@link Persona}
	 */
	public Persona guardarEmpleado(Persona empleado);

	/**
	 * Método para obtener los Personas por tipoRol
	 * 
	 * @param tipoRol  tipo de persona
	 * @param pageable par describir el paginado
	 * @return {@link Persona}
	 */
	public Page<Persona> obtenerEmpleadosPorTipo(String tipoRol, Pageable pageable);

	public Persona obtenerPersonaPorCorreo(String correo);

	public boolean eliminarEmpleado(Long idPersona);

	public Persona actualizarEmpleado(PersonaDto personaDto);

	/**
	 * Método para validar si existe el objeto Vacuna
	 * 
	 * @param personaDto
	 * @return
	 */
	default Vacuna validarExistenciaVacuna(PersonaDto personaDto) {
		if (personaDto.getEmpleado().getEstadoVacunacionPersona() && personaDto.getVacuna() != null) {
			return personaDto.getVacuna();
		} else
			return null;
	}
	
	
	

}
