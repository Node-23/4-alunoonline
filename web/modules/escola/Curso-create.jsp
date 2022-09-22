<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="turma" type="modules.escola.beans.Curso"--%>


<script type="text/javascript">
    function goBack() {
        window.history.back();
    }
</script>

<div class="container">
	<h1 style="text-align: center;">Novo Curso</h1>
	<br/>
	<br/>
	<fpg:hasError>
		<div style="color: red; border: solid 1px red">
			<fpg:error />
		</div>
	</fpg:hasError>

	<form method="post" action="<fpg:contextPath/>/escola/Curso-create">
		<div class="form-group">
			<label for="nome">Nome</label>
			<input class="form-control" id="nome" name="nome" value="${curso.nome}" />
		</div>
		<div class="form-group">
			<label for="codigo">Código do Curso</label>
			<input class="form-control" id="codigo" name="codigo" value="${curso.codigo}" />
		</div>
		<div class="form-group">
			<label for="tipo">Tipo do Curso</label>
			<fpg:Select id="tipo" list="tipos" defaultText="" name="tipo" selected="${curso.tipo.id}" showAttr="nome"/>
		</div>
		<button type="submit" class="btn btn-primary" value="Salvar">Salvar</button>
	</form>
	<br/>
	<button class="btn btn btn-secondary" onclick="goBack()">Voltar</button>
</div>
