package modules.escola.actions;

import modules.escola.beans.Curso;
import modules.escola.dao.CursoDao;
import modules.escola.dao.TipoCursoDao;
import modules.escola.enums.TipoFiltroTurmaEnum;
import modules.escola.validators.CursoValidator;
import org.futurepages.core.persistence.Dao;
import org.futurepages.menta.actions.CrudActions;


public class CursoActions extends CrudActions {

	@Override
	protected void restoreObject() {
		Curso curso = (Curso) input.getValue("curso");
		output("curso", Dao.getInstance().get(Curso.class, curso.getId()));

	}

	@Override
	protected void listDependencies() {
		Curso curso = (Curso) input.getValue("curso");
		if (hasError()) {
			output("curso", curso);
		}

		output("tipos", TipoCursoDao.listAll());
	}

	public String create() {
		Curso curso = (Curso) input.getValue("curso");
		validate(CursoValidator.class).createOrUpdate(curso);
		Dao.getInstance().saveTransaction(curso);
		return success("Curso criado com sucesso.");
	}

	public String update() {
		Curso cursoForm = (Curso) input.getValue("curso");
		validate(CursoValidator.class).createOrUpdate(cursoForm);
		Curso cursoDB = CursoDao.getById(cursoForm.getId());
		cursoDB.fillFromForm(cursoForm);

		Dao.getInstance().updateTransaction(cursoDB);
		return success("Curso excluído com sucesso.");
	}

	public String delete() {
		Curso curso = (Curso) input.getValue("curso");
		curso = CursoDao.getById(curso.getId());
		Dao.getInstance().deleteTransaction(curso);
		return success("Curso excluído com sucesso.");
	}

	public String explore(String busca, String tipoFiltro){
		TipoFiltroTurmaEnum tipoFiltroTurma = null;
		try {
			tipoFiltroTurma = TipoFiltroTurmaEnum.valueOf(tipoFiltro);
		}catch (Exception ignored){}

		output("cursos", CursoDao.listByWithFilter(busca, tipoFiltroTurma));
		output("busca",busca);

		TipoFiltroTurmaEnum[] opcoesFiltroCurso = tipoFiltroTurma.values();
		output("opcoesFiltroCurso", opcoesFiltroCurso);
		return SUCCESS;
	}

	@Override
	protected void listObjects() { explore("", null); }
}
