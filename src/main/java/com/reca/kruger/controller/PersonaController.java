package com.reca.kruger.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reca.kruger.config.Configuraciones;
import com.reca.kruger.domain.dto.PersonaDto;
import com.reca.kruger.domain.repository.PersonaRepository;
import com.reca.kruger.domain.repository.VacunaRepository;
import com.reca.kruger.domain.service.IPersonaService;
import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Vacuna;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin/empleados")
@Api(tags = "Administracion de Empleados")
@Slf4j
public class PersonaController {

	@Autowired
	IPersonaService personaService;

	@Autowired
	PersonaRepository perRepository;

	@Autowired
	VacunaRepository vacunaRepository;

	@PostMapping(value = "/guardar")
	@ApiOperation(value = "Guardar Empleados ", notes = "")
	public ResponseEntity<Persona> guardarEmpleado(@RequestBody Persona empleado) {
		empleado = personaService.guardarEmpleado(empleado);

		if (empleado != null) {
			return new ResponseEntity<>(empleado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping(value = "/actualizar")
	@ApiOperation(value = "Actualizar Empleados ", notes = "")
	public ResponseEntity<Persona> actualizarEmpleado(@RequestBody PersonaDto empleadoDto) {
		Persona empleado = personaService.actualizarEmpleado(empleadoDto);

		if (empleado != null) {
			return new ResponseEntity<>(empleado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(value = "/por-estado/{esatdoVacuna}")
	@ApiOperation(value = "Buscar Empleados por estado", notes = "")
	public ResponseEntity<List<Persona>> obtnenerPersonasPorEsatdoVacuna(
			@PathVariable("esatdoVacuna") String estadoVacuna) {
		log.info("{}", estadoVacuna);
		List<Persona> lstpersonas = personaService.obtenerPersonasPorEstadoVacuna(estadoVacuna);

		if (lstpersonas != null) {
			return new ResponseEntity<List<Persona>>(lstpersonas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/por-fecha/{fechaInicio}/{fechaFinal}")
	@ApiOperation(value = "Buscar Empleados por fecha vacunación", notes = "")
	public ResponseEntity<List<Persona>> obtnenerPersonasPorFechaVacuna(
			@PathVariable("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
			@PathVariable("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal) {
		log.info("{}", fechaInicio);
		List<Persona> lstpersonas = personaService.obtenerPersonasPorFechaVacuna(fechaInicio, fechaFinal);

		if (lstpersonas != null) {
			return new ResponseEntity<List<Persona>>(lstpersonas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/por-tipo/{tipoVacuna}")
	@ApiOperation(value = "Buscar Empleados por tipo vacuna", notes = "")
	public ResponseEntity<List<Persona>> obtnenerPersonasPorTipoVacuna(@PathVariable("tipoVacuna") String tipoVacuna) {
		log.info("{}", tipoVacuna);
		List<Persona> lstpersonas = personaService.obtenerPersonasPorTipoVacuna(tipoVacuna);

		if (lstpersonas != null) {
			return new ResponseEntity<List<Persona>>(lstpersonas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/todos")
	@ApiOperation(value = "Listar todos los empleados", notes = "")
	public Page<Persona> obtnenerEmpleados(Pageable pageable) {
		return personaService.obtenerEmpleadosPorTipo(Configuraciones.PERFIL.EMPLEADO.getTipo(), pageable);
	}

	@GetMapping(value = "/busqueda")
	@ApiOperation(value = "Buscar Empleados por tipo vacuna", notes = "Se puede realizar la busqueda por uno o tres criterios de busqueda, <br> ->Por tipo Vacuna <br>"
			+ "->fecha vacuna <br> ->esatdo vacunación, <br> Ej. http://localhost:8090/api/admin/empleados/busqueda?estadoVacuna=S&tipoVacuna=Sputnik <br> ->"
			+ "http://localhost:8090/api/admin/empleados/busqueda?fechaInicial=2021-04-04&fechaFinal=2022-04-04&estadoVacuna=S&tipoVacuna=Pfizer")
	public List<Persona> busquedaPorTipoFechaEsatdoVacuna(

			@Conjunction(value = {
					@Or({ @Spec(path = "tipoVacuna", params = "tipoVacuna", spec = LikeIgnoreCase.class) }),
					@Or({ @Spec(path = "persona.estadoVacunacionPersona", params = "estadoVacuna", spec = Equal.class) }),
					@Or({ @Spec(path = "fechaVacuna", params = { "fechaInicial",
							"fechaFinal" }, spec = Between.class) }), }) Specification<Vacuna> empresaSpec) {

		List<Vacuna> lstVacuna = vacunaRepository.findAll(empresaSpec);
		List<Persona> lstPersona = lstVacuna.stream().map(p -> p.getPersona()).collect(Collectors.toList());

		return lstPersona;
	}

	@GetMapping(value = "/por-user/{user}")
	@ApiOperation(value = "Obtener el empleado or id", notes = "")
	public Persona obtnenerEmpleadoPorUse(@PathVariable("user") String user) {
		return personaService.obtenerPersonaPorCorreo(user);
	}

}
