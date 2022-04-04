package com.reca.kruger.domain.service;

import com.reca.kruger.persistence.entity.Clave;

public interface IClaveService {

	Clave guardarClave(Clave clave,Long idEmpleado);

}
