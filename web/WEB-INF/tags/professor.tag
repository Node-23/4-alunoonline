<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="params" type="java.util.Map"--%>

<%@attribute name="professor" type="modules.escola.beans.Professor"  required="true"%>
<%@attribute name="green" type="java.lang.Boolean"  required="true"%>

<tr ${green? 'style="color:green"':'style="color:red"'}>
    <td>${professor.id}</td>
    <td style="text-align: center"><img src="${params.UPLOADS_URL_PATH}/professores/${professor.id}.jpg" style="width: 24px;" alt="Foto do professor"/></td>
    <td>${professor.nomeCompleto}</td>
    <td>${professor.matricula}</td>
    <td>${professor.curso.nome}</td>
    <td>${professor.ListarTurmas()}</td>
    <td colspan="2">
        <a class="btn btn-warning" href="<fpg:contextPath/>/escola/Professor?type=update&id=${professor.id}">editar</a>
        <a class="btn btn-danger" href="javascript:confirmaExclusao('${professor.id}', '${professor.nomeCompleto}', '${professor.matricula}');" >apagar</a>
    </td>
</tr>