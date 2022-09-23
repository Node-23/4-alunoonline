package modules.escola.actions;

import install.Resources;
import modules.escola.beans.Aluno;
import modules.escola.beans.Curso;
import modules.escola.beans.Professor;
import modules.escola.beans.Turma;
import modules.escola.dao.AlunoDao;
import modules.escola.dao.CursoDao;
import modules.escola.dao.ProfessorDao;
import modules.escola.dao.TurmaDao;
import modules.escola.enums.TipoFiltroProfessorTurmaEnum;
import modules.escola.validators.ProfessorValidator;
import org.apache.commons.fileupload.FileItem;
import org.futurepages.core.persistence.Dao;
import org.futurepages.core.persistence.annotations.Transactional;
import org.futurepages.enums.PathTypeEnum;
import org.futurepages.menta.actions.CrudActions;
import org.futurepages.util.Is;
import org.futurepages.util.The;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfessorActions extends CrudActions {

    /*
     * É função executada ao chamar-se Professor-create.jsp
     */
    @Transactional
    public String create() throws Exception {
        Professor professor = (Professor) input.getValue("professor");
        FileItem foto = (FileItem) input.getValue("foto");
        validate(ProfessorValidator.class).createOrUpdate(professor, foto);
        Dao.getInstance().save(professor);
        gravaFoto(professor,foto);
        return success("Professor criado com sucesso");
    }

    /*
     * Ação de atualização do professor no arquivo  Professor-create.jsp
     */
    @Transactional
    public String update() throws Exception {
        Professor professor = (Professor) input.getValue("professor");
        FileItem foto = (FileItem) input.getValue("foto");
        validate(ProfessorValidator.class).createOrUpdate(professor, foto);
        Professor professorDB = ProfessorDao.getById(professor.getId());
        professorDB.fillFromUpdateForm(professor);
        Dao.getInstance().updateTransaction(professorDB);

        gravaFoto(professor,foto);
        return success("Professor atualizado com sucesso");
    }

    /*
     * listObjects() é a filtragem padrão da listagem.
     * É o filtro default da listagem. Entenda quando é chamado em CrudActions.execute()
     * É quando executado quando chama-se Professor.jsp?type=explore
     */
    @Override
    protected void listObjects() {
        explore(0, null);
    }

    /*
     * É a execução da filtragem.
     *
     * É executado quando chamamos Professor-explore.jsp
     */
    public String explore(int cursoId, String tipoFiltroName) {
        TipoFiltroProfessorTurmaEnum tipoFiltro = null;
        try {
            tipoFiltro = TipoFiltroProfessorTurmaEnum.valueOf(tipoFiltroName);
        }catch (Exception ignored){}
        Curso curso = Is.selected(cursoId)? Dao.getInstance().get(Curso.class, cursoId) : null;
        TipoFiltroProfessorTurmaEnum[] opcoesFiltroTurma = TipoFiltroProfessorTurmaEnum.values();

        // lista principal...
        output("professores", ProfessoresFiltrados(curso, tipoFiltro));
        // dependencias de filtragem
        output("opcoesFiltroTurma", opcoesFiltroTurma);
        //filtros selecionados
        output("tipoFiltro", tipoFiltro); //filtro tipo turma realizado
        output("cursos", CursoDao.listAll());
        return SUCCESS;
    }

    @SuppressWarnings("PointlessBooleanExpression")
    public List<Professor> ProfessoresFiltrados(Curso curso, TipoFiltroProfessorTurmaEnum filtro){
        List<Professor> professoresFiltrados;
        if(curso != null){
            professoresFiltrados = ProfessorDao.professoresFiltradosPorCurso(curso);
        }else{
            professoresFiltrados = ProfessorDao.listAll();
        }

        if(filtro == TipoFiltroProfessorTurmaEnum.PROFESSOR_COM_TURMA){
            professoresFiltrados.removeIf(professor -> professor.getTurmas().isEmpty() == true);
            return professoresFiltrados;
        }

        if(filtro == TipoFiltroProfessorTurmaEnum.PROFESSOR_SEM_TURMA){
            professoresFiltrados.removeIf(professor -> professor.getTurmas().isEmpty() == false);
            return professoresFiltrados;
        }
        return professoresFiltrados;
    }

    /*
     * Chamado ao confirmar exclusão do professor
     * OBS: Antes de deletar o professor, remove a referência deste nas turmas
     */
    @Transactional
    public String delete() {
        Professor professor = (Professor) input.getValue("professor");
        for (Turma turma: TurmaDao.listAll()) {
            if(turma.getProfessor() == null){
                continue;
            }
            if(turma.getProfessor().getId() == professor.getId()){
                turma.RemoverProfessor();
                Dao.getInstance().updateTransaction(turma);
            }
        }
        professor = ProfessorDao.getById(professor.getId());
        Dao.getInstance().delete(professor);
        return success("Professor excluído com sucesso.");
    }

    /*
     * método chamado ao editar um professor. Ele coloca os dados do professor selecionado
     * nos devidos inputs
     */
    @Override
    protected void restoreObject() {
        Professor professor = (Professor) input.getValue("professor");

        professor = ProfessorDao.getById(professor.getId());
        output("professor", professor);
    }

    private void gravaFoto(Professor professor, FileItem foto) throws Exception {
        if(foto == null) return;
        String uploadsPath = Resources.getUploadsPath(PathTypeEnum.REAL);
        String path = The.concat(uploadsPath, "/professores/", String.valueOf(professor.getId()),".jpg");
        File file = new File(path);
        foto.write(file);
    }

    /*
     * Metodo usado para retornar as dependencias necessarias tanto na criação quanto na atualização
     * do professor
     */
    @Override
    protected void listDependencies() {
        //quando tem erro no formulário, para recarregar a tela, deve-se colocar novamente o objeto no output.
        if(hasError()) {
            fwdValue("professor");
        }
        List<Turma> turmas = TurmaDao.listAll();
        List<Curso> cursos = CursoDao.listAll();
        output("turmas", turmas);
        output("cursos", cursos);
    }
}
