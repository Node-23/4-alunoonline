package install.escola;

import modules.escola.beans.Curso;
import modules.escola.beans.Professor;
import modules.escola.beans.TipoTurma;
import modules.escola.beans.Turma;
import modules.escola.dao.CursoDao;
import modules.escola.dao.TipoTurmaDao;
import org.apache.commons.lang.math.RandomUtils;
import org.futurepages.core.install.Installation;
import org.futurepages.core.persistence.Dao;

import java.util.List;
import java.util.Random;

import static org.futurepages.core.persistence.HQLProvider.*;

/*
 * Instala turmas para testes de exemplos
 */
public class Turmas implements Installation {

    private List<Professor> listaProfessores;

    public Turmas(List<Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

	public void execute() {

		insereTurma("T01",  "Cálculo I", obtemProfessorRandomico());
        insereTurma("T02",  "Introdução a computação", obtemProfessorRandomico());
        insereTurma("T03",  "Lógica", obtemProfessorRandomico());
        insereTurma("T04",  "Estatística", obtemProfessorRandomico());
        insereTurma("T05",  "Programação I", obtemProfessorRandomico());
        insereTurma("T06",  "Programação II", obtemProfessorRandomico());
        insereTurma("T07",  "Cálculo II", obtemProfessorRandomico());
        insereTurma("T08",  "Estrutura de dados", obtemProfessorRandomico());
        insereTurma("T09",  "Arquitetura de computadores");
        insereTurma("T10",  "Redes de computadores I", obtemProfessorRandomico());
        insereTurma("T011", "Sistemas Operacionais I");
        insereTurma("T012", "Redes de computadores II", obtemProfessorRandomico());
        insereTurma("T013", "Sistemas Operacionais II");

    }

	private void insereTurma(String codigo, String nome) {
        insereTurma(codigo, nome, null);
	}

    private Professor obtemProfessorRandomico() {
        return listaProfessores.get(modulo(rand.nextInt()) % listaProfessores.size());
    }
    private final Random rand = new Random(System.currentTimeMillis());
    private int modulo(int n) {
        return ((n < 0) ? (-n) : n);
    }

    private void insereTurma(String codigo, String nome, Professor professor) {
        Turma turma = new Turma(codigo, nome);

        int totalTipos = (int) Dao.getInstance().numRows(hql(TipoTurma.class));
        TipoTurma tipo = TipoTurmaDao.getById(RandomUtils.nextInt(totalTipos) + 1);
        turma.setTipo(tipo);
        turma.setProfessor(professor);
        turma.setCurso(professor.getCurso());
        Dao.getInstance().save(turma);
    }
}
