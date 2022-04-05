package com.reca.kruger.domain.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reca.kruger.config.Configuraciones;
import com.reca.kruger.domain.dto.PersonaDto;
import com.reca.kruger.domain.repository.PersonaRepository;
import com.reca.kruger.domain.repository.RolRepository;
import com.reca.kruger.domain.repository.VacunaRepository;
import com.reca.kruger.domain.service.IPersonaService;
import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Rol;
import com.reca.kruger.persistence.entity.Vacuna;

@Service
public class PersonaServiceImp implements IPersonaService {

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	VacunaRepository vacunaRepository;

	@Override
	public List<Persona> obtenerPersonasPorEstadoVacuna(String estadoVacuna) {

		if (estadoVacuna != null) {

			return personaRepository.findByEstadoVacunacionPersona(estadoVacuna);
		} else {
			return null;
		}

	}

	@Override
	public List<Persona> obtenerPersonasPorTipoVacuna(String tipoVacuna) {
		try {
			return personaRepository.findByVacunas_TipoVacuna(tipoVacuna);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Persona> obtenerPersonasPorFechaVacuna(Date fechaInicial, Date fechaFinal) {
		try {
			return personaRepository.findByVacunas_FechaVacunaBetween(fechaInicial, fechaFinal);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public Persona guardarEmpleado(Persona empleado) {
		try {
			Rol rol = rolRepository.findByTipoRol(Configuraciones.PERFIL.EMPLEADO.getTipo());
			empleado.setRol(rol);
			empleado.setEstadoVacunacionPersona(Configuraciones.ESTADO_VACUNA.NO_VACUNADO.getEsatdo());
			return personaRepository.save(empleado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Page<Persona> obtenerEmpleadosPorTipo(String tipoRol, Pageable pageable) {

		try {
			return personaRepository.findAllByRolPersona(tipoRol, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Persona obtenerPersonaPorCorreo(String correo) {
		try {
			return personaRepository.findByCorreoPersona(correo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean eliminarEmpleado(Long idPersona) {
		try {
			personaRepository.deleteById(idPersona);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Persona actualizarEmpleado(PersonaDto personaDto) {
		try {
			Vacuna vacuna = this.validarExistenciaVacuna(personaDto);
			if (vacuna != null) {
				personaDto.getEmpleado().setRol(rolRepository.findByTipoRol(Configuraciones.PERFIL.EMPLEADO.getTipo()));
				Persona persona = personaRepository.save(personaDto.getEmpleado());
				vacuna.setPersona(persona);
				vacunaRepository.save(vacuna);
				return persona;
			} else {
				return personaRepository.save(personaDto.getEmpleado());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Persona obtenerEmpleadoPorId(Long idEmpleado) {
		try {
			return personaRepository.getById(idEmpleado);
		} catch (Exception e) {
			System.out.println("Entra en el error");
			e.printStackTrace();
			return null;
		}

	}

}
