<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
	<jsp:include page="../components/libs.jsp"/>
</head>
<body>
		<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Código</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a></li>
	        <li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
	    </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>
	<div class="container">
		<h1>Cadastro de Produto</h1>
		<form:form action="${s:mvcUrl('PC#gravar').build()}" method="post" commandName="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label> 
				<form:input path="titulo" cssClass="form-control"/>
				<form:errors path="titulo"/>
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" rows="10" cols="20" cssClass="form-control" />
				<form:errors path="descricao"/>
			</div>
			<div class="form-group">
				<label>Data lançamento</label> 
				<form:input path="dataLancamento" />
				<form:errors path="dataLancamento"/>
			</div>
			<div class="form-group">
				<label>Páginas</label> 
				<form:input path="paginas" />
				<form:errors path="paginas"/>
			</div>
			<form:errors path="precos"/>
			<h3>Preços</h3>
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor" cssClass="form-control"/>
					<form:hidden path="precos[${status.index}].preco" value="${tipoPreco}"/>
					</div>
			</c:forEach>
			<div class="form-group">
				<label>Imagem sumário</label>
				<input type="file" name="sumario"  class="form-control">
			</div>
			<button type="submit">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>