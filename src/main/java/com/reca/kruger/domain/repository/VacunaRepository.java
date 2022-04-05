package com.reca.kruger.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.reca.kruger.persistence.entity.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> ,JpaSpecificationExecutor<Vacuna>{

}
