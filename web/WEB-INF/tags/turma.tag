<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="aluno" type="modules.escola.beans.Curso"--%>
<%@attribute name="turma" type="modules.escola.beans.Turma"  required="true"%>

<tr>
	<td>${turma.curso.nome}</td>
	<td>${turma.id}</td>
	<td>${turma.codigo}</td>
	<td>${turma.nome}</td>
	<td>${turma.tipo.nome}</td>
	<td>${turma.professor.nomeCompleto}</td>
	<td>${turma.totalAlunos}</td>
	<td>${turma.representante.nomeCompleto}</td>
	<td>
		<fpg:list value="turma.alunos">
			<fpg:loop var="aluno">
				<%--@elvariable id="aluno" type="modules.escola.beans.Aluno"--%>
				${aluno.nomeCompleto}<br/>
			</fpg:loop>
		</fpg:list>
	</td>
	<td colspan="2">
		<a class="btn btn-warning" href="<fpg:contextPath/>/escola/Turma?type=update&id=${turma.id}">editar</a>
		<a class="btn btn-danger" href="javascript:confirmaExclusao('${turma.id}', '${turma.codigo}', '${turma.nome}', ${turma.curso == null});" >apagar</a>
	</td>
</tr>