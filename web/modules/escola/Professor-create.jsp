<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="professor" type="modules.escola.beans.Professor"--%>
<%--@elvariable id="turma" type="modules.escola.beans.Curso"--%>

<div>
    <h1 style="text-align: center;">Novo Professor</h1>
    <br/>
    <br/>
    <fpg:hasError>
        <div style="color: red; border:solid 1px red">
            <fpg:error/>
        </div>
    </fpg:hasError>
    <br/>

    <form method="post" action="<fpg:contextPath/>/escola/Professor-create" enctype="multipart/form-data">
        <div class="form-group" style="display: inline-flex">
            <div style="width: 50%; padding: 20px">
                <label for="nomeCompleto">Nome</label>
                <input class="form-control" id="nomeCompleto" name="nomeCompleto" value="${professor.nomeCompleto}" />
            </div>
            <div style="width: 50%;padding: 20px">
                <label for="matricula">Matrícula</label>
                <input class="form-control" id="matricula" name="matricula" value="${professor.matricula}" />
            </div>
        </div>
        <div style="padding: 20px">
            <label for="curso">Curso</label>
            <fpg:Select id="curso"
                        list="cursos"
                        name="curso"
                        selected="${professor.curso!=null?professor.curso.id:0}"
                        showAttr="nome"
                        defaultText="- Sem curso -"
                        defaultValue=""
                        style="margin-top: 8px; margin-left: 5px;"
            />
        </div>
        <div style="display: inline-flex">
            <div style="padding-left: 20px">
                <label for="foto">Foto 3x4</label>
                <input style="width: 390px" class="form-control" type="file" id="foto" name="foto"/>
            </div>
            <div style="padding-top: 32px; padding-left: 10px">
                <button type="submit" class="btn btn-primary" value="Salvar"> Salvar </button>
            </div>
        </div>

    </form>

</div>