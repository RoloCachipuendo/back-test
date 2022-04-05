package com.reca.kruger.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredencialDto {
	
	private Long id;
	private String nickUsuario;	
	private String claveUsuario;
	private String rol;
	

}
