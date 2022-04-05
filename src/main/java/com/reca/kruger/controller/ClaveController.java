package com.reca.kruger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reca.kruger.domain.dto.CredencialDto;
import com.reca.kruger.domain.service.IClaveService;
import com.reca.kruger.persistence.entity.Clave;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin/claves")
@Api(tags = "Administracion de Claves")
@Slf4j
public class ClaveController {

	@Autowired
	IClaveService claveService;

	@PostMapping(value = "/crear/{idEmpleado}")
	@ApiOperation(value = "Crear clave del empleado", notes = "")
	public Clave guardarClave(@PathVariable("idEmpleado") Long idEmpleado) {
		try {
			return claveService.guardarClave(idEmpleado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping(value = "/login")
	@ApiOperation(value = "Crear clave del empleado", notes = "")
	public ResponseEntity<CredencialDto> iniciarSesion(@RequestBody CredencialDto credencial) {
		log.info("Entra en el login");
		try {
			credencial = claveService.validarLogueo(credencial);
			if (credencial != null) {
				return new ResponseEntity<CredencialDto>(credencial, HttpStatus.OK);
			} else {
				return new ResponseEntity<CredencialDto>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CredencialDto>(HttpStatus.BAD_REQUEST);
		}
	}

}
