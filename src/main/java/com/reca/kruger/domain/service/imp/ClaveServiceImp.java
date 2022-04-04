package com.reca.kruger.domain.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reca.kruger.domain.repository.ClaveRepository;
import com.reca.kruger.domain.repository.PersonaRepository;
import com.reca.kruger.domain.service.IClaveService;
import com.reca.kruger.persistence.entity.Clave;
import com.reca.kruger.persistence.entity.Persona;


@Service
public class ClaveServiceImp implements IClaveService {

	@Autowired
	ClaveRepository claveRespository;

	@Autowired
	PersonaRepository personaRepository;

	@Override
	public Clave guardarClave(Clave clave, Long idEmpleado) {
		Persona persona = personaRepository.findById(idEmpleado).get();
		clave.setPersona(persona);
		return claveRespository.save(clave);
	}

}
