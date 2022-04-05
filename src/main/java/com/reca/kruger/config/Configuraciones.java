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

	public enum ESTADO_VACUNA {
		VACUNADO("S"), 
		NO_VACUNADO("N"),;

		private final String estado;

		private ESTADO_VACUNA(String estado) {
			this.estado = estado;
		}

		public String getEsatdo() {
			return estado;
		}

	}
}
