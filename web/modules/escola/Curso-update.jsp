<%@taglib uri="futurepagesApp" prefix="fpg" %>

<div style="text-align: center;">
	<h2>Editar Curso</h2>
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
	<form method="post" action="<fpg:contextPath/>/escola/Curso-update" enctype="multipart/form-data">
		<%--@elvariable id="turma" type="modules.escola.beans.Curso"--%>
		<input name="id" value="${curso.id}" type="hidden" />
		Código: <input id="codigo" name="codigo" value="${curso.codigo}" />
		<br />
		<br />
		Nome: <input id="nome" name="nome" value="${curso.nome}" />
		<br />
		<br />
		Tipo: <fpg:Select list="tipos" defaultText="" name="tipo" selected="${curso.tipo.id}" showAttr="nome"/>
		<br />
		<br />
		<input type="submit" value="Atualizar" />
	</form>
</div>
