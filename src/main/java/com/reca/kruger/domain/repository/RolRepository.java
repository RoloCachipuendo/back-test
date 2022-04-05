package com.reca.kruger.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>,JpaSpecificationExecutor<Rol>{
	
	Rol findByTipoRol(String tipoRol);
	
	Rol findByPersonas(Persona persona);

}
