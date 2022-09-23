<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="professor" type="modules.escola.beans.Professor"--%>
<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>
<%--@elvariable id="curso" type="modules.escola.beans.Curso"--%>
<%--@elvariable id="tipoFiltro" type="modules.escola.enums.TipoFiltroProfessorTurmaEnum"--%>

<script type="text/javascript">
    function confirmaExclusao(id, nomeCompleto, matricula) {
        if(confirm("Deseja realmente apagar o professor " + nomeCompleto + "\n(matricula: " + matricula + ")")) {
            document.location = '<fpg:contextPath/>/escola/Professor-delete?id=' + id;
        }
    }
</script>

<div align="center">
    <h2>Listagem de Professores</h2>
    <fpg:hasSuccess>
        <div style="text-align: center; width: 400px; color:green; border-color: green; background-color: greenyellow">
            <fpg:success/>
        </div>
        <br/>
        <br/>
    </fpg:hasSuccess>
    <a href="<fpg:contextPath/>/escola/Professor?type=create">Novo Professor</a>
    <br/>
    <br/>
    <form id="form-filtro-professores" method="get" action="<fpg:contextPath/>/escola/Professor-explore">
        <fpg:Select list="cursos"
                    name="curso"
                    id="curso"
                    selected="${curso!=null? curso.id : 0}"
                    defaultText="Filtrar por curso"
                    onchange="$('#form-filtro-professores').submit()"
                    showAttr="nome"
                    style="
                            border-color: transparent transparent #fff transparent;
                            width: 200 px;
                            height: 35px;
                            text-align: center;
                          "
        />
        <fpg:Select list="opcoesFiltroTurma"
                    showAttr="rotulo"
                    name="tipoFiltroName"
                    selected="${tipoFiltro!=null? tipoFiltro.name() : ''}"
                    idName="id"
                    defaultValue=""
                    onchange="$('#form-filtro-professores').submit()"
                    defaultText="Lista Geral"
                    style="
                            border-color: transparent transparent #fff transparent;
                            width: 200 px;
                            height: 35px;
                            text-align: center;
                          "
        />
    </form>
    <br/>
    <br/>
    <fpg:list value="professores">
        <fpg:isEmpty>
            Nenhum Professor cadastrado.
        </fpg:isEmpty>
        <fpg:isEmpty negate="true">
            <div style="margin-left: 80px; margin-right: 80px">
                <table class="table table-bordered table-striped"
                       id="table"
                       data-toggle="table"
                       style="text-align: center;">
                    <tr>
                        <td data-field="id"><strong>ID</strong></td>
                        <td><strong>Foto</strong></td>
                        <td data-field="codigo"><strong>Nome</strong></td>
                        <td data-field="codigo"><strong>Matrícula</strong></td>
                        <td data-field="codigo"><strong>Curso</strong></td>
                        <td data-field="codigo"><strong>Turmas</strong></td>
                        <td colspan="2" style="text-align: center;"><strong>Ações</strong></td>
                    </tr>
                    <fpg:loop var="professor">
                        <fpg:professor professor="${professor}" green="${!(professor.turmas.isEmpty() || professor.turmas == null)}"/>
                    </fpg:loop>
                </table>
            </div>
        </fpg:isEmpty>
    </fpg:list>
    <br/>
    <br/>
</div>