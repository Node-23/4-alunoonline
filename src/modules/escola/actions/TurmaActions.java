package modules.escola.actions;

import modules.escola.Service.TurmaService;
import modules.escola.beans.Turma;
import modules.escola.dao.AlunoDao;
import modules.escola.dao.CursoDao;
import modules.escola.dao.ProfessorDao;
import modules.escola.dao.TipoTurmaDao;
import modules.escola.dao.TurmaDao;
import modules.escola.enums.TipoFiltroTurmaEnum;
import modules.escola.validators.TurmaValidator;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.annotations.Transactional;
import org.futurepages.menta.actions.CrudActions;

//Crud Actions de Turma
public class TurmaActions extends CrudActions {

	@Override
	protected void restoreObject() {
		Turma turma = (Turma) input.getValue("turma");
        output("turma", Dao.getInstance().get(Turma.class, turma.getId()));
	}

	@Override
    protected void listDependencies() {
		Turma turma = (Turma) input.getValue("turma");
		//quando tem erro no formulário, para recarregar a tela, deve-se colocar novamente o objeto no output.
		if (hasError()) {
			output("turma", turma);
		}

		output("tipos", TipoTurmaDao.listAll());
		output("alunos", AlunoDao.listByTurmaId(turma.getId()));
		output("professores", ProfessorDao.listAll());
		output("cursos", CursoDao.listAll());
	}

    public String create() {
		Turma turma = (Turma) input.getValue("turma");
        validate(TurmaValidator.class).createOrUpdate(turma);
        Dao.getInstance().saveTransaction(turma);
        return success("Turma criada com sucesso.");
    }

	public String update() {
		Turma turmaForm = (Turma) input.getValue("turma");
		validate(TurmaValidator.class).createOrUpdate(turmaForm);
		Turma turmaDB = TurmaDao.getById(turmaForm.getId());
		turmaDB.fillFromForm(turmaForm);

		Dao.getInstance().updateTransaction(turmaDB);
		return success("Turma atualizada com sucesso.");
	}

	@Transactional
	public String delete() {
		Turma turma = (Turma) input.getValue("turma");
		turma = TurmaDao.getById(turma.getId());
		TurmaService.deleteTurmaService(turma);
		return success("Turma excluída com sucesso.");
	}



	public String explore(String busca, String tipoFiltro){
		TipoFiltroTurmaEnum tipoFiltroTurmaEnum = null;
		try {
			tipoFiltroTurmaEnum = TipoFiltroTurmaEnum.valueOf(tipoFiltro);
		}catch (Exception ignored){}

		output("turmas", TurmaDao.listByWithFilter(busca, tipoFiltroTurmaEnum));
		output("busca",busca);

		TipoFiltroTurmaEnum[] opcoesFiltroTurma = tipoFiltroTurmaEnum.values();
		output("opcoesFiltroTurma", opcoesFiltroTurma);
		return SUCCESS;
	}

    @Override
    protected void listObjects() {
		explore("", null);
    }

}
