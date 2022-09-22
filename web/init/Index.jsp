<%--@elvariable id="mensagemInicial" type="java.lang.String"--%>
<%--@elvariable id="momentoAtual" type="java.lang.String"--%>

<%@taglib uri="futurepagesApp" prefix="fpg"%>

<div style="text-align:center">
    <h1>${mensagemInicial}</h1>
	<h2>
	    <fpg:valueFormatter object="${momentoAtual}" formatter="fullDateLiteral"/>
	</h2>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Curso?type=create" >Novo Curso</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Curso?type=explore" >Listar Cursos</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Turma?type=create" >Nova Turma</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Turma?type=explore" >Listar Turmas</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Aluno?type=create">Novo Aluno</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Aluno?type=explore">Listar Alunos</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Professor?type=create">Novo Professor</a>
    <br/>
    <br/>
    <a href="<fpg:contextPath/>/escola/Professor?type=explore">Listar Professores</a>
    <br/>
    <br/>
</div>