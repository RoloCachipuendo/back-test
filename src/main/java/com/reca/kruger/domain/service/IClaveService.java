package com.reca.kruger.domain.service;

import com.reca.kruger.domain.dto.CredencialDto;
import com.reca.kruger.persistence.entity.Clave;

public interface IClaveService {

	Clave guardarClave(Long idEmpleado);

	CredencialDto validarLogueo(CredencialDto credencial);

}
