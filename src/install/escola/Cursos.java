package install.escola;

import modules.escola.beans.Curso;
import modules.escola.beans.TipoCurso;
import modules.escola.beans.TipoTurma;
import modules.escola.dao.TipoCursoDao;
import org.apache.commons.lang.math.RandomUtils;
import org.futurepages.core.install.Installation;
import org.futurepages.core.persistence.Dao;

import static org.futurepages.core.persistence.HQLProvider.hql;


public class Cursos implements Installation {
	public Cursos(){}
	private void installCurso(String codigo, String nome) {
		Curso curso = new Curso(codigo, nome);
		int totalTipos = (int) Dao.getInstance().numRows(hql(TipoTurma.class));
		TipoCurso tipo = TipoCursoDao.getById(RandomUtils.nextInt(totalTipos) + 1);
		curso.setTipo(tipo);
		Dao.getInstance().save(curso);
	}

	public void execute() {
		installCurso("C01",  "Ciência da Computação");
		installCurso("C02",  "Engenharia da Computação");
		installCurso("C03",  "Informática");
		installCurso("C04",  "Análise e Desenvolvimento de Sistemas");
		installCurso("C05",  "Desenvolvedor de Software");;
	}
}
