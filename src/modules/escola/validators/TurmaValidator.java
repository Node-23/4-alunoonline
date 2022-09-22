package modules.escola.validators;

import modules.escola.beans.Turma;
import modules.escola.dao.TurmaDao;
import org.futurepages.menta.core.validation.Validator;
import org.futurepages.util.Is;
import modules.escola.beans.Curso;

public class TurmaValidator extends Validator {

    public void createOrUpdate(Turma turma) {

        // validando o nome da turma
        if (Is.empty(turma.getNome())) {
            error("Preencha o nome da turma.");
        }

        // validação do código está vazio
        if (Is.empty(turma.getCodigo())) {
            error("Preencha o código da turma.");
        }
        // verifica se o código da turma já está cadastrado.
        else if (TurmaDao.getComMesmoCodigoDesta(turma)!=null) {
            error("Já existe uma turma com esse código.");
        }

        if(turma.getRepresentante()!=null && TurmaDao.getComMesmoRepresentante(turma) != null){
            error("Já existe uma turma com esse representante.");
        }

        if(turma.getProfessor() == null){
            error("Selecione um professor");
        }

	    if(turma.getTipo()==null){
            error("Informe o tipo da turma.");
	    }

    }
}
