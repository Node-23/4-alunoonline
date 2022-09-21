package modules.escola.dao;

import modules.escola.beans.Aluno;
import modules.escola.beans.Professor;
import modules.escola.beans.Turma;
import modules.escola.enums.TipoFiltroAlunoTurmaEnum;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.HQLProvider;
import org.futurepages.util.Is;

import java.util.List;

public class ProfessorDao extends HQLProvider {
    private static final String  DEFAULT_ORDER = asc("nomeCompleto");

    public static List<Professor> listAll() {
        return Dao.getInstance().list(hql(Professor.class,null, DEFAULT_ORDER));
    }
    public static Professor getById(int id) {
        return Dao.getInstance().get(Professor.class,id);
    }
    public static Professor getOutroComMatriculaDeste(Professor professor) {
        return Dao.getInstance().uniqueResult(hql(Professor.class,
                ands(field("id").differentFrom(professor.getId()),
                        field("matricula").equalsTo(professor.getMatricula())
                )));
    }
}
