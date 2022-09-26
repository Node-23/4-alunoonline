<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="busca" type="java.lang.String"--%>
<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>
<%--@elvariable id="curso" type="modules.escola.beans.Curso"--%>



<script type="text/javascript">
    function confirmaExclusao(id, codigo, nome) {
        if(confirm("Deseja realmente apagar o curso \ncodigo:" + codigo + "\nnome: " + nome + " ")) {
            document.location = '<fpg:contextPath/>/escola/Curso-delete?id=' + id;
        }
    }
</script>
<script>
    function detailFormatter(index, row) {
        var html = []
        html.push('<h5>Lista de Turmas:</h5>')
        $.each(row, function (key, value) {
            if(key === 'lista-turmas'){
                if(value !== ""){
                    html.push('<p>' + value + '</p>')
                }else{
                    html.push('<p> Lista vazia. </p>')
                }

            }
        })
        return html.join('')
    }
</script>

<div class="container">
	<h2>Listagem de Cursos</h2>
	<fpg:hasSuccess>
		<div style="text-align: center; width: 400px; color:green; border-color: green; background-color: greenyellow">
			<fpg:success />
		</div>
		<br />
		<br />
	</fpg:hasSuccess>
	<a href="<fpg:contextPath/>/escola/Curso?type=create">Novo Curso</a>
	&nbsp;
	<form id="form-filtro-turma" style="display:inline;" method="get" action="<fpg:contextPath/>/escola/Curso-explore">
		<input type="text" name="busca" value="${busca}"/>
		<input type="submit" value="Buscar"/>
	</form>
	<br />
	<br />
	<fpg:list value="cursos">
		<fpg:isEmpty>
			Nenhum curso cadastrado.
		</fpg:isEmpty>
		<fpg:isEmpty negate="true">
			<table class="table table-bordered table-striped"
			       id="table"
			       data-toggle="table"
			       data-detail-view="true"
			       data-detail-formatter="detailFormatter"
			       style="text-align: center;">
				<thead>
				<tr>
					<th data-field="id">ID</th>
					<th data-field="codigo">CÓDIGO</th>
					<th data-field="nome">NOME</th>
					<th data-field="tipo">TIPO</th>
					<th data-field="total-turmas">TOTAL DE TURMAS</th>
					<th data-field="lista-turmas" data-visible="false">LISTA DE TURMAS</th>
					<th>Ações</th>
				</tr>
				</thead>
				<tbody>
				<fpg:loop var="curso">
					<fpg:curso curso="${curso}"/>
				</fpg:loop>
				</tbody>
			</table>
		</fpg:isEmpty>
	</fpg:list>
	<br />
	<br />
</div>
