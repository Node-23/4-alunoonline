package modules.escola.validators;

import modules.escola.beans.Curso;
import modules.escola.dao.CursoDao;
import org.futurepages.util.Is;
import org.futurepages.menta.core.validation.Validator;

public class CursoValidator extends Validator{

	public void createOrUpdate(Curso curso){


		// Validaçao do nome do Curso
		if (Is.empty(curso.getNome())){
			error("Preencha o nome do curso.");
		}

		// Validação do código
		if (Is.empty(curso.getCodigo())) {
			error("Preencha o código do curso.");
		}

		// verifica se o código do curso já está cadastrado.
		else if (CursoDao.getComMesmoCodigoDeste(curso)!=null) {
			error("Já existe um curso com esse código.");
		}

		if(curso.getTipo()==null){
			error("Informe o tipo do curso.");
		}
	}
}
