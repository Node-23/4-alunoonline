package modules.escola.install;

import modules.escola.beans.TipoCurso;
import org.futurepages.core.install.Installer;
import org.futurepages.core.persistence.Dao;

public class TiposCursoInstaller extends Installer {

	@Override
	public void execute() throws Exception {
		Dao.getInstance().save(new TipoCurso("Curso Matinal"));
		Dao.getInstance().save(new TipoCurso("Curso Vespertino"));
		Dao.getInstance().save(new TipoCurso("Curso Matutino"));
		Dao.getInstance().save(new TipoCurso("Curso Pré-Matutino"));
		Dao.getInstance().save(new TipoCurso("Curso Integral"));
	}
}
