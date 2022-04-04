package com.reca.kruger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reca.kruger.domain.service.IClaveService;
import com.reca.kruger.persistence.entity.Clave;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/admin/claves")
@Api(tags = "Administracion de Claves")
public class ClaveController {
	
	@Autowired
	IClaveService claveService;
	
	@PostMapping(value = "/crear/{idEmpleado}")
	@ApiOperation(value = "Crear clave del empleado", notes = "")
	public Clave guardarClave(@RequestBody Clave clave,@PathVariable("idEmpleado") Long idEmpleado) {		
		try {
			return claveService.guardarClave(clave, idEmpleado);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
