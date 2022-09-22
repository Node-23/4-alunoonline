package modules.escola.enums;

import static org.futurepages.core.persistence.HQLProvider.*;

public enum TipoFiltroTurmaEnum {
		TURMA_SEM_REPRESENTANTE("Turma sem representante", field("representante").isNull()),
		TURMA_COM_REPRESENTANTE("Turma com representante", field("representante").isNotNull()),
		TURMA_SEM_CURSO("Turma sem curso", field("curso").isNull()),
		TURMA_COM_CURSO("Turma com curso", field("curso").isNotNull());

		private final String rotulo;
		private final String whereHQL;

		TipoFiltroTurmaEnum(String rotulo, String whereHQL) {
			this.rotulo = rotulo;
			this.whereHQL = whereHQL;
		}

		public String getRotulo() {
			return rotulo;
		}

		public String getWhereHQL() {
			return whereHQL;
		}

		public String getId(){
			return name();
		}


}
