package modules.escola.install;

import install.Resources;
import org.futurepages.core.install.Installer;
import org.futurepages.enums.PathTypeEnum;
import org.futurepages.util.The;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Instala as turmas e depois os alunos.
 */
public class EscolaInstaller extends Installer{

    @Override
    public void execute() throws Exception {

	    File uploadsDir = new File(Resources.getUploadsPath(PathTypeEnum.REAL)+"/alunos");
	    if(!uploadsDir.exists()){
		    uploadsDir.mkdirs();
	    }

		//Cria a pasta de fotos dos professores no apache
		String uploadsPath = Resources.getUploadsPath(PathTypeEnum.REAL);
		Path professorFolderPath = Paths.get(The.concat(uploadsPath, "/professores/"));
		if(Files.notExists(professorFolderPath)){
			Files.createDirectories(professorFolderPath);
		}
    }
}