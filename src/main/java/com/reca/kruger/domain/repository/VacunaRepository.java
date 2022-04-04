package com.reca.kruger.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reca.kruger.persistence.entity.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {

}
