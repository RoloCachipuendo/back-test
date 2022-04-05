package com.reca.kruger.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.reca.kruger.persistence.entity.Clave;
import com.reca.kruger.persistence.entity.Persona;;

public interface ClaveRepository extends JpaRepository<Clave, Long>, JpaSpecificationExecutor<Clave> {
	
	List<Clave> findByPersona(Persona persona);

}
