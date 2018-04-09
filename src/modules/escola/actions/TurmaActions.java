package modules.escola.actions;

import modules.escola.beans.Turma;
import modules.escola.dao.TipoTurmaDao;
import modules.escola.dao.TurmaDao;
import modules.escola.validators.TurmaValidator;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.annotations.Transactional;
import org.futurepages.menta.actions.CrudActions;

//Crud Actions de Turma
public class TurmaActions extends CrudActions {

    private Turma turma; //objeto injetado conforme os filtros.

	@Override
	protected void restoreObject() {
        output("turma", Dao.getInstance().get(Turma.class, turma.getId()));
	}

	@Override
    protected void listDependencies() {

		//quando tem erro no formulário, para recarregar a tela, deve-se colocar novamente o objeto no output.
		if (hasError()) {
			output("turma", turma);
		}

		output("tipos", TipoTurmaDao.listAll());
    }

	@Transactional
    public String create() {
        validate(TurmaValidator.class).create(turma);
        Dao.getInstance().save(turma);
        return success("Turma criada com sucesso.");
    }

    @Override
    protected void listObjects() {
        output("turmas", TurmaDao.listAll());
    }
}
