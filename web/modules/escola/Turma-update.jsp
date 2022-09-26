<%@taglib uri="futurepagesApp" prefix="fpg" %>
<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>

<div style="text-align: center;">
    <h2>Editar Turma</h2>
    <br/>
    <br/>
    <fpg:hasError>
        <!-- imprime estre pedaço somente se houver erro na página -->
        <div style="color: red; border: solid 1px red;">
            <fpg:error/>
        </div>
    </fpg:hasError>
    <br />
    <br />
    <a href="<fpg:contextPath />">Voltar ao início</a>
    <br />
    <br />
    <form method="post" action="<fpg:contextPath/>/escola/Turma-update" enctype="multipart/form-data">
        <input name="id" value="${turma.id}" type="hidden" />
        Código da Turma: <input id="codigo" name="codigo" value="${turma.codigo}" />
        <br />
        <br />
        Nome: <input id="nome" name="nome" value="${turma.nome}" />
        <br />
        <br />
        Tipo: <fpg:Select list="tipos" defaultText="" name="tipo" selected="${turma.tipo.id}" showAttr="nome"/>
        <br />
        <br />
        Representante:
        <fpg:Select list="alunos" name="representante" defaultText="Selecione..." defaultValue="0" selected="${turma.representante.id}" showAttr="nomeCompleto"/>
        <br />
        <br />
        Curso:
        <fpg:Select id="curso" list="cursos" defaultText="Selecione..." defaultValue="0" name="curso" selected="${turma.curso.id}" showAttr="nome"/>
        <br />
        <br />
            <div style="padding: 20px">
                <label>Professor</label>
                <fpg:ajaxSelect
                    id="professor"
                    name="professor"
                    defaultText=" Sem professor "
                    sourceValue="curso"
                    url="/escola/CarregaProfessoresDoCurso"
                    selected="${turma.professor!=null?turma.professor.id:0}"
                    style="margin-top: 8px; margin-left: 5px;"
                />
            </div>
        <input type="submit" value="Atualizar" />
    </form>
</div>
