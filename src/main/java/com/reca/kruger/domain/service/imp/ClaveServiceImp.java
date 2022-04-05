package com.reca.kruger.domain.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reca.kruger.domain.dto.CredencialDto;
import com.reca.kruger.domain.repository.ClaveRepository;
import com.reca.kruger.domain.repository.PersonaRepository;
import com.reca.kruger.domain.repository.RolRepository;
import com.reca.kruger.domain.service.IClaveService;
import com.reca.kruger.persistence.entity.Clave;
import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Rol;

@Service
public class ClaveServiceImp implements IClaveService {

	@Autowired
	ClaveRepository claveRespository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolrepository;

	@Override
	public Clave guardarClave(Long idEmpleado) {
		Persona persona = personaRepository.findById(idEmpleado).get();
		Clave clave = Clave.builder().descripcionClave("emp2022").fechaClave(new Date()).persona(persona).build();
		return claveRespository.save(clave);
	}

	@Override
	public CredencialDto validarLogueo(CredencialDto credencial) {
		Persona usuario = personaRepository.findByCorreoPersona(credencial.getNickUsuario());

		if (usuario != null) {
			Rol rol = rolrepository.findByPersonas(usuario);
			Clave clave = claveRespository.findByPersona(usuario).stream().sorted().findFirst().orElse(null);
			if (clave.getDescripcionClave().equals(credencial.getClaveUsuario())) {
				credencial.setId(usuario.getIdPersona());
				credencial.setRol(rol.getTipoRol());
				credencial.setClaveUsuario("*******");
				return credencial;
			} else {
				return null;
			}
		} else
			return null;

	}

}
