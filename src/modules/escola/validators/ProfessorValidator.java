package modules.escola.validators;

import modules.escola.beans.Professor;
import modules.escola.dao.AlunoDao;
import modules.escola.dao.ProfessorDao;
import org.apache.commons.fileupload.FileItem;
import org.futurepages.menta.core.validation.Validator;
import org.futurepages.util.Is;

public class ProfessorValidator extends Validator {
    public void createOrUpdate(Professor professor, FileItem foto){
        if (Is.empty(professor.getNomeCompleto())) {
            error("Preencha o nome do professor.");
        }

        if (Is.empty(professor.getMatricula())) { //verifica se matrícula vazia
            error("Especifique a matrícula do professor.");
        }
        else {
            // Caso matrícula não seja vazia, verifica se a matrícula já existe
            if (ProfessorDao.getOutroComMatriculaDeste(professor) != null) {
                error("Já existe um professor com esta matrícula.");
            }
        }
        if(foto==null){
            error("A foto 3x4 é obrigatória.");
        }else if(!foto.getContentType().equals("image/jpeg")){
            error("A foto precisa ser JPG válida.");
        }
    }
}
