package modules.escola.dao;

import modules.escola.beans.TipoCurso;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.HQLProvider;

import java.util.List;

public class TipoCursoDao extends HQLProvider {

	private static final String DEFAULT_ORDER = asc("nome");

	public static TipoCurso getById(int id){
		return Dao.getInstance().uniqueResult(hql(TipoCurso.class, field("id").equalsTo(id)));
	}

	public static List<TipoCurso> listAll() { return Dao.getInstance().list(hql(TipoCurso.class, null, DEFAULT_ORDER)); }
}
