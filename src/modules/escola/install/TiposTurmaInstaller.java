package modules.escola.install;

import modules.escola.beans.TipoTurma;
import org.futurepages.core.install.Installer;
import org.futurepages.core.persistence.Dao;

/*
 * Instala os tipos de turma...
 */
public class TiposTurmaInstaller extends Installer{

	/**
	 * Metodo que é executado ao carregar a aplicacao
	 * @throws Exception
	 */
    @Override
    public void execute() throws Exception {
	    Dao.getInstance().save(new TipoTurma("Turma Matinal"));
	    Dao.getInstance().save(new TipoTurma("Turma Vespertina"));
	    Dao.getInstance().save(new TipoTurma("Turma Matutino"));
	    Dao.getInstance().save(new TipoTurma("Turma Pré-Matutino"));
	    Dao.getInstance().save(new TipoTurma("Turma Integral"));
    }

	public static void main(String[] args) {
		Dao.getInstance().beginTransaction();
		new TiposTurmaInstaller();
		Dao.getInstance().commitTransaction();
		Dao.getInstance().close();
	}
}