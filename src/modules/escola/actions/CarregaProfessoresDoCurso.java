package modules.escola.actions;

import modules.escola.beans.Curso;
import modules.escola.beans.Professor;
import modules.escola.dao.ProfessorDao;
import org.futurepages.menta.actions.AjaxAction;
import org.futurepages.menta.actions.FreeAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarregaProfessoresDoCurso extends FreeAction implements AjaxAction {


    @Override
    public String execute(){
        int cursoId = input.getIntValue("sourceValue");
        Curso curso = new Curso();
        curso.setId(cursoId);
        List<Professor> professoresList = ProfessorDao.professoresFiltradosPorCurso(curso);
        Map<String, String> mapProfessores = new HashMap<>();
        for (Professor professor: professoresList) {
            mapProfessores.put(String.valueOf(professor.getId()), professor.getNomeCompleto());
        }
        outputAjax(mapProfessores);
        return SUCCESS;
    }
}
