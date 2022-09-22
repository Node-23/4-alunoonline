package modules.escola.dao;

import modules.escola.beans.Curso;
import modules.escola.beans.Turma;
import modules.escola.enums.TipoFiltroTurmaEnum;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.HQLProvider;
import java.util.List;

public class CursoDao extends HQLProvider {

	private static final String DEFAULT_ORDER = asc("codigo");

	public static List<Curso> listAll() {
		return Dao.getInstance().list(hql(Curso.class, null ,DEFAULT_ORDER));
	}

	public static Curso getComMesmoCodigoDeste(Curso curso) {
		return Dao.getInstance().uniqueResult(hql(Curso.class, ands(
												field("id").differentFrom(curso.getId()),
												field("codigo").equalsTo(curso.getCodigo())
								)

			)
		);
	}

	public static Curso getById(int id) { return Dao.getInstance().get(Curso.class,id); }

	public static long getTotalTurmasPara(Curso curso) {
		return Dao.getInstance().numRows(hql(Turma.class, field("curso").equalsTo(curso)));
	}

	public static List<Curso> listBy(String busca){
		return Dao.getInstance().list(hql(Curso.class , field("name").matches(busca)));
	}

	public static Object listByWithFilter(String busca, TipoFiltroTurmaEnum tipoFiltro) {
		return Dao.getInstance().list(hql(Curso.class,
						ands(
								busca!=null? field("nome").matches(busca) : "",
								tipoFiltro!=null? tipoFiltro.getWhereHQL() : ""
						),
						DEFAULT_ORDER
				)
		);
	}

}
