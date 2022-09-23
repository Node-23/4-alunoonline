package modules.escola;

import modules.escola.actions.AlunoActions;
import modules.escola.actions.ProfessorActions;
import modules.escola.actions.CursoActions;
import modules.escola.actions.TurmaActions;
import modules.escola.beans.Aluno;
import modules.escola.beans.Professor;
import modules.escola.beans.TipoTurma;
import modules.escola.beans.Turma;
import modules.escola.beans.*;
import org.futurepages.menta.core.control.AbstractModuleManager;
import org.futurepages.menta.filters.PIFilter;
import org.futurepages.menta.filters.VOFilter;

public class ModuleManager extends AbstractModuleManager {

    @Override
    public void loadActions() {

        //Configuração da Action com o apelido "Aluno"
        action("Aluno", AlunoActions.class)
            // VOFilter: pega os valores dos campos do formulário da visão e
            // casa-os com os atributos da classe Aluno.class, instancia um
            // objeto deste tipo e insere os valores dos campos do formulário
            // nas respectivas propriedades, o objeto preenchido é atribuido
            // ao campo da action com o nome "aluno".
            .filter(new VOFilter("aluno", Aluno.class))

            // PIFIlter: recupera do banco uma "turma" de acordo com o id
            // adquirida no formulário e insere-o em um objeto "aluno".
            // Retirando assim, esta tarefa do programador.
            .filter(new PIFilter("aluno", "turma", Turma.class))

            // se chamar Aluno?type=create, o método execute será chamado, e
            // a consequência do método deverá mandar o resultado do output
            // (fwIn) para a tela de criação do aluno dentro do módulo
            // (com o template montado)
            .on(CREATE, fwIn("Aluno-create.page"))

            // se ocorrer sucesso na criação do aluno vai para a tela de
            // listagem dos alunos cadastrados
            .on(SUCCESS, CREATE, chainIn("Aluno?type=explore"))

            // se ocorrer error na criacao do aluno volta para a tela de
            // criação do aluno
            .on(ERROR,   CREATE, fwIn("Aluno-create.page"))

            // Aluno?type=explore ocorre o redirecionamento para a tela
            // de listagem dos alunos cadastrados no sistema
            .on(EXPLORE, fwIn("Aluno-explore.page"))

            // Aluno-explore.fpg retorna SUCCESS e redireciona para a
            // tela de listagem de alunos cadastrados, filtrados por
            // turma.
            .on(SUCCESS, EXPLORE, fwIn("Aluno-explore.page"))

            // Se type for igual a UPDATE a consequência será a tela
            // de atualização
            .on(UPDATE, fwIn("Aluno-update.page"))

            // Se update retornar SUCCESS a consequência será a tela
            // de exploração. (OBS: não foi implementado a consequência
            // caso update retorne ERROR. Isto fica à cargo do estudante.)
            .on(SUCCESS, UPDATE, chainIn("Aluno?type=explore"))

            .on(ERROR, UPDATE, fwIn("Aluno-update.page"))

            // Se delete retornar SUCCESS a consequência será a tela
            // de exploração. (OBS: não foi implementado a consequência
            // caso delete retorne ERROR. Isto fica à cargo do estudante.)
            .on(SUCCESS, DELETE, chainIn("Aluno?type=explore"));



        // Configuração da Action "Turma"
        // TurmaActions recebe um apelido "Turma"
        action("Turma", TurmaActions.class)

            //VOFilter: pega os valores dos campos do formulário da visão e
            // casa-os com os atributos da classe Turma.class, instancia um
            // objeto deste tipo e insere os valores dos campos do formulário
            // nas respectivas propriedades, o objeto preenchido é atribuido
            // ao campo da action com o nome "turma".
            .filter(new VOFilter("turma", Turma.class))
            .filter(new PIFilter("turma","tipo", TipoTurma.class))
            .filter(new PIFilter("turma","representante", Aluno.class))
            .filter(new PIFilter("turma","curso", Curso.class))

            .filter(new PIFilter("turma", "professor", Professor.class))

            // se chamar Turma?type=create, o método execute será chamado, e
            // a consequência do método deverá mandar o resultado do output
            // (fwIn) para a tela de criação do aluno dentro do módulo
            // (com o template montado)
            .on(CREATE, fwIn("Turma-create.page"))

            // se ocorrer sucesso na criação de turma vai para a tela de
            // listagem das turmas cadastrados
            .on(SUCCESS, CREATE, chainIn("Turma?type=explore"))

            // se ocorrer error na criacao da turma volta para a tela de
            // criação de turma
            .on(ERROR, CREATE, fwIn("Turma-create.page"))

            // Turma?type=explore ocorre o redirecionamento para a tela
            // de listagem das turmas cadastrados no sistema
            .on(EXPLORE, fwIn("Turma-explore.page"))

            // Se type for igual a UPDATE a consequência será a tela
            // de atualização
            .on(UPDATE, fwIn("Turma-update.page"))

            .on(SUCCESS, UPDATE, chainIn("Turma?type=explore"))

            .on(SUCCESS, DELETE, chainIn("Turma?type=explore"));

        action("Professor", ProfessorActions.class)
                //VOFilter deve sempre ficar antes dos PIFilter
                .filter(new VOFilter("professor", Professor.class))
                .filter(new PIFilter("professor","curso", Curso.class))
                .on(CREATE, fwIn("Professor-create.page"))
                .on(SUCCESS, CREATE, chainIn("Professor?type=explore"))
                .on(ERROR,   CREATE, fwIn("Professor-create.page"))
                .on(EXPLORE, fwIn("Professor-explore.page"))
                .on(SUCCESS, EXPLORE, fwIn("Professor-explore.page"))
                .on(UPDATE, fwIn("Professor-update.page"))
                .on(SUCCESS, UPDATE, chainIn("Professor?type=explore"))
                .on(ERROR, UPDATE, fwIn("Professor-update.page"))
                .on(SUCCESS, DELETE, chainIn("Professor?type=explore"));


        action("Curso", CursoActions.class)

                //VOFilter: pega os valores dos campos do formulário da visão e
                // casa-os com os atributos da classe Curso.class, instancia um
                // objeto deste tipo e insere os valores dos campos do formulário
                // nas respectivas propriedades, o objeto preenchido é atribuido
                // ao campo da action com o nome "turma".
                .filter(new VOFilter("curso", Curso.class))
                .filter(new PIFilter("curso","tipo", TipoCurso.class))

                // se chamar Curso?type=create, o método execute será chamado, e
                // a consequência do método deverá mandar o resultado do output
                // (fwIn) para a tela de criação de turmas dentro do módulo
                // (com o template montado)
                .on(CREATE, fwIn("Curso-create.page"))

                // se ocorrer sucesso na criação de curso vai para a tela de
                // listagem dos cursos cadastrados
                .on(SUCCESS, CREATE, chainIn("Curso?type=explore"))

                // se ocorrer error na criacao do curso volta para a tela de
                // criação de curso
                .on(ERROR, CREATE, fwIn("Curso-create.page"))

                // Curso?type=explore ocorre o redirecionamento para a tela
                // de listagem dos cursos cadastrados no sistema
                .on(EXPLORE, fwIn("Curso-explore.page"))

                // Se type for igual a UPDATE a consequência será a tela
                // de atualização
                .on(UPDATE, fwIn("Curso-update.page"))

                .on(SUCCESS, UPDATE, chainIn("Curso?type=explore"))

                .on(SUCCESS, DELETE, chainIn("Curso?type=explore"));
    }
}
