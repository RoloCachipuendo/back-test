package com.reca.kruger.domain.dto;

import com.reca.kruger.persistence.entity.Persona;
import com.reca.kruger.persistence.entity.Vacuna;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PersonaDto {
	
	private Persona empleado;
	private Vacuna vacuna;

}
