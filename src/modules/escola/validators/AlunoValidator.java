package modules.escola.validators;

import modules.escola.beans.Aluno;
import modules.escola.dao.AlunoDao;
import org.apache.commons.fileupload.FileItem;
import org.futurepages.menta.core.validation.Validator;
import org.futurepages.util.FileUtil;
import org.futurepages.util.Is;

import java.io.File;

import static org.futurepages.menta.core.action.Manipulable.CREATE;

public class AlunoValidator extends Validator {

	public void createOrUpdate(Aluno aluno, FileItem foto, String type) {


		// Validação do nome
		if (Is.empty(aluno.getNomeCompleto())) {
			error("Preencha o nome do aluno.");
		}

		// Validação da matrícula
		if (Is.empty(aluno.getMatricula())) { //verifica se matrícula vazia
			error("Especifique a matrícula do aluno.");
		} else {
			// Caso matrícula não seja vazia, verifica se a matrícula já existe
			if (AlunoDao.getOutroComMatriculaDeste(aluno) != null) {
				error("Já existe um aluno com esta matrícula.");
			}
		}

        // Validação da turma
        if (Is.empty(aluno.getTurma())) {
            error("Selecione uma turma.");
        }

		if(foto==null){ //&& type.equals(CREATE)){
			error("A foto 3x4 é obrigatória.");
		}else if(!foto.getContentType().equals("image/jpeg")){
			error("A foto precisa ser JPG válida.");
		}
	}
}
