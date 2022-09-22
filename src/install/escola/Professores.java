package install.escola;

import install.Resources;
import modules.escola.beans.Professor;
import org.futurepages.core.install.Installation;
import org.futurepages.core.persistence.Dao;
import org.futurepages.enums.PathTypeEnum;
import org.futurepages.util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Dao.getInstance().save(professor);
        Path path = Paths.get(FileUtil.classRealPath(this.getClass()) + "res\\professores\\" + professor.getId() + ".jpg");
        if(Files.notExists(path)){
            FileUtil.copy(FileUtil.classRealPath(this.getClass()) + "res\\Default.jpg", Resources.getUploadsPath(PathTypeEnum.REAL) + "\\professores\\" + professor.getId()+".jpg");
        }else{
            FileUtil.copy(FileUtil.classRealPath(this.getClass()) + "res\\professores\\" + professor.getId() + ".jpg", Resources.getUploadsPath(PathTypeEnum.REAL) + "\\professores\\" + professor.getId()+".jpg");
        }
    }
}
