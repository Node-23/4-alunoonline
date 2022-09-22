<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>
<%--@elvariable id="professor" type="modules.escola.beans.Professor"--%>

<script type="text/javascript">
    function goBack() {
        window.history.back();
    }
</script>

<div class="container">
    <h1 style="text-align: center;">Nova Turma</h1>
    <br/>
    <br/>
    <fpg:hasError>
        <div style="color: red; border: solid 1px red">
            <fpg:error />
        </div>
    </fpg:hasError>

    <form method="post" action="<fpg:contextPath/>/escola/Turma-create">
        <div class="form-group">
            <label for="nome">Nome</label>
            <input class="form-control" id="nome" name="nome" value="${turma.nome}" />
        </div>
        <div class="form-group">
            <label for="codigo">Códido da Turma</label>
            <input class="form-control" id="codigo" name="codigo" value="${turma.codigo}" />
        </div>
        <div class="form-group">
            <label for="tipo">Tipo</label>
            <fpg:Select id="tipo" list="tipos" defaultText="" name="tipo" selected="${turma.tipo.id}" showAttr="nome"/>
        </div>
        <div style="padding: 20px">
            <label for="professor">Professor</label>
            <fpg:Select id="professor"
                        list="professores"
                        name="professor"
                        selected="${turma.professor!=null?turma.professor.id:0}"
                        showAttr="nomeCompleto"
                        defaultText="- Sem professor -"
                        defaultValue=""
                        style="margin-top: 8px; margin-left: 5px;"
            />
        </div>
        <button type="submit" class="btn btn-primary" value="Salvar">Salvar</button>
    </form>
    <br/>
    <button class="btn btn btn-secondary" onclick="goBack()">Voltar</button>
</div>
