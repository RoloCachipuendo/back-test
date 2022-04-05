package com.reca.kruger.domain.repository;


import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.reca.kruger.persistence.entity.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {

	List<Persona> findByEstadoVacunacionPersona(String estadoVacuna);

	List<Persona> findByVacunas_TipoVacuna(String tipoVacuna);

	List<Persona> findByVacunas_FechaVacunaBetween(Date fechaInicial, Date fechaFinal);

	@Query(value = "select * from persona p inner join  rol r "
			+ "	on p.id_rol =r.id_rol where r.tipo_rol =:rolEmpleado", nativeQuery = true)
	Page<Persona> findAllByRolPersona(String rolEmpleado, Pageable pageable);
	
	Persona findByCorreoPersona(String correoPersona);

}
