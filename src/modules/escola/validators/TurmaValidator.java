package modules.escola.validators;

import modules.escola.beans.Turma;
import modules.escola.dao.TurmaDao;
import org.futurepages.menta.core.validation.Validator;
import org.futurepages.util.Is;
import static org.futurepages.menta.core.action.Manipulable.UPDATE;

public class TurmaValidator extends Validator {

    public void createOrUpdate(Turma turma, String type) {

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

        if(turma.getProfessor() == null && type.equals(UPDATE)){
            error("Selecione um professor");
        }

        if(turma.getCurso() == null && type.equals(UPDATE)){
            error("Selecione um curso");
        }
        if(turma.getProfessor().getCurso() == null){
            error("Esse professor não possui um curso, adicione-o a um curso antes de adicioná-lo a uma turma");
        }

        if(turma.getProfessor().getCurso().getId() != turma.getCurso().getId()){
            error("O professor precisa ser do mesmo curso que a turma!");
        }

	    if(turma.getTipo()==null){
            error("Informe o tipo da turma.");
	    }

    }
}
