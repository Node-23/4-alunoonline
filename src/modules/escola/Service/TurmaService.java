package modules.escola.Service;

import modules.escola.beans.Aluno;
import modules.escola.beans.Turma;
import org.futurepages.core.persistence.Dao;

public class TurmaService {
    public static void deleteTurmaService(Turma turma){
        if(!turma.getAlunos().isEmpty()){
            removeATurmaDosAlunos(turma);
        }
        Dao.getInstance().delete(turma);
    }

    private static void removeATurmaDosAlunos(Turma turma){

        for (Aluno aluno: turma.getAlunos()) {
            aluno.setTurma(null);
            Dao.getInstance().update(aluno);
        }
        turma.setAlunos(null);
    }
}
