<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="futurepagesApp" prefix="fpg"%>

<%@attribute name="curso" type="modules.escola.beans.Curso"  required="true"%>

<tr>
	<td>${curso.id}</td>
	<td>${curso.codigo}</td>
	<td>${curso.nome}</td>
	<td>${curso.tipo.nome}</td>
	<td>${curso.totalTurmas}</td>
	<td>
		<fpg:list value="curso.turmas">
			<fpg:loop var="turma">
				<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>
				${turma.nome}<br/>
			</fpg:loop>
		</fpg:list>
	</td>
	<td colspan="2">
		<a class="btn btn-warning" href="<fpg:contextPath/>/escola/Curso?type=update&id=${curso.id}">editar</a>
		<a class="btn btn-danger" href="javascript:confirmaExclusao('${curso.id}', '${curso.codigo}', '${curso.nome}');" >apagar</a>
	</td>
</tr>