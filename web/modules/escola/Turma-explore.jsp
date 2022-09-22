<%@taglib uri="futurepagesApp" prefix="fpg"%>
<%--@elvariable id="busca" type="java.lang.String"--%>
<%--@elvariable id="turma" type="modules.escola.beans.Turma"--%>
<%--@elvariable id="tipoFiltro" type="modules.escola.enums.TipoFiltroTurmaEnum"--%>



<script type="text/javascript">
    function confirmaExclusao(id, codigo, nome, cursoIsNull) {

        if(cursoIsNull){
            alert("A turma" + codigo + "\nnome: " + nome + " possui um curso a ela. \nRetire o curso primeiramente!")
        }else{
	        if(confirm("Deseja realmente apagar a turma \ncodigo:" + codigo + "\nnome: " + nome + " ")) {
	            document.location = '<fpg:contextPath/>/escola/Turma-delete?id=' + id;
	        }
        }

    }
</script>
<script>
	function detailFormatter(index, row) {
		var html = []
		html.push('<h5>Lista de Alunos:</h5>')
		$.each(row, function (key, value) {
			if(key == 'lista-alunos'){
				if(value != ""){
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
    <h2>Listagem de Turmas</h2>
    <fpg:hasSuccess>
		<div style="text-align: center; width: 400px; color:green; border-color: green; background-color: greenyellow">
			<fpg:success />
		</div>
        <br />
        <br />
    </fpg:hasSuccess>
        <a href="<fpg:contextPath/>/escola/Turma?type=create">Nova Turma</a>
    &nbsp;
	<form id="form-filtro-turma" style="display:inline;" method="get" action="<fpg:contextPath/>/escola/Turma-explore">
		<input type="text" name="busca" value="${busca}"/>
		<input type="submit" value="Buscar"/>
		<fpg:Select list="opcoesFiltroTurma"
		            showAttr="rotulo"
		            name="tipoFiltroName"
		            selected="${tipoFiltro!=null? tipoFiltro.name() : ''}"
		            idName="id"
		            defaultValue=""
		            onchange="$('#form-filtro-turma').submit()"
		            defaultText="- Lista Geral -"
		/>
	</form>
	<br />
    <br />
    <fpg:list value="turmas">
        <fpg:isEmpty>
            Nenhuma turma cadastrada.
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
		                <th data-field="curso">NOME DO CURSO</th>
		                <th data-field="id">ID</th>
	                    <th data-field="codigo">CÓDIGO</th>
	                    <th data-field="nome">NOME</th>
	                    <th data-field="tipo">TIPO</th>
						<th data-field="professor">PROFESSOR</th>
	                    <th data-field="total-alunos">TOTAL DE ALUNOS</th>
		                <th data-field="representante">REPRESENTANTE</th>
		                <th data-field="lista-alunos" data-visible="false">LISTA DE ALUNOS</th>
	                    <th>Ações</th>
	                </tr>
	            </thead>
	            <tbody>
                <fpg:loop var="turma">
					<fpg:turma turma="${turma}"/>
                </fpg:loop>
	            </tbody>
            </table>
        </fpg:isEmpty>
    </fpg:list>
    <br />
    <br />
</div>
