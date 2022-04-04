package com.reca.kruger.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.reca.kruger.persistence.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
	
	Rol findByTipoRol(String tipoRol);

}
