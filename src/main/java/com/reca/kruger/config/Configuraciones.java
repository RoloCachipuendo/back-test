package com.reca.kruger.config;

public interface Configuraciones {

	public enum PERFIL {
		ADMIN("ADMIN"), 
		EMPLEADO("EMP"),;

		private final String tipo;

		private PERFIL(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}

	}

}
