package install;

import install.escola.Alunos;
import install.escola.Cursos;
import install.escola.Professores;
import install.escola.Turmas;
import modules.escola.beans.Professor;
import modules.escola.beans.Turma;
import org.futurepages.core.install.Installer;
import org.futurepages.core.persistence.Dao;

import java.util.List;

public class Examples extends Installer {

	@Override
	public void execute() throws Exception {
		install(new Cursos());
		install(new Professores());
		List<Professor> professores = Dao.getInstance().list(Professor.class);
		install(new Turmas(professores));

		List<Turma> turmas = Dao.getInstance().list(Turma.class);
		install(new Alunos(turmas));
	}
}