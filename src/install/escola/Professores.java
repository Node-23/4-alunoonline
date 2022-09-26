package install.escola;

import install.Resources;
import modules.escola.beans.Curso;
import modules.escola.beans.Professor;
import modules.escola.beans.TipoCurso;
import modules.escola.beans.TipoTurma;
import modules.escola.dao.CursoDao;
import modules.escola.dao.TipoCursoDao;
import org.apache.commons.lang.math.RandomUtils;
import org.futurepages.core.install.Installation;
import org.futurepages.core.persistence.Dao;
import org.futurepages.enums.PathTypeEnum;
import org.futurepages.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.futurepages.core.persistence.HQLProvider.hql;

public class Professores implements Installation {
    public Professores() {}
    @Override
    public void execute() throws Exception {
        installProfessor("2015P1312", "Jorge García Soares");
        installProfessor("2013P1309", "Thiago Mattus Primus");
        installProfessor("2020P1415", "Exemplildo Costa Gomes");
        installProfessor("2012P1302", "Fulanilson Gabeira Lemos");
        installProfessor("2021P1421", "Simara Marta Lima da Silva");
        installProfessor("2008P1104", "Liânio Leal Lopes");
    }

    private void installProfessor(String matricula, String nome) throws IOException {
        Professor professor = new Professor(matricula, nome);
        int totalCursos = (int) Dao.getInstance().numRows(hql(Curso.class));
        Curso curso = CursoDao.getById(RandomUtils.nextInt(totalCursos) + 1);
        professor.setCurso(curso);
        Dao.getInstance().save(professor);
        String path = FileUtil.classRealPath(this.getClass()) + "res/professores/" + professor.getId() + ".jpg";

        if(new File(path).exists()){
            FileUtil.copy(FileUtil.classRealPath(this.getClass()) + "res/professores/" + professor.getId() + ".jpg", Resources.getUploadsPath(PathTypeEnum.REAL) + "/professores/" + professor.getId()+".jpg");
        }else{
            FileUtil.copy(FileUtil.classRealPath(this.getClass()) + "res/Default.jpg", Resources.getUploadsPath(PathTypeEnum.REAL) + "/professores/" + professor.getId()+".jpg");
        }
    }
}
