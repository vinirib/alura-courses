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
	<jsp:include page="./components/libs.jsp"/>
</head>
<body>
	<div class="container">
		<h1>Cadastro de Usuário</h1>
		<form:form  action="${s:mvcUrl('LC#gravar').build()}" method="post" commandName="usuario">
			<div class="form-group">
				<label>Nome</label> 
				<form:input path="nome" cssClass="form-control"/>
				<form:errors path="nome"/>
			</div>
			<div class="form-group">
				<label>Email</label> 
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email"/>
			</div>
			<div class="form-group">
				<label>Senha</label> 
				<form:password path="senha" cssClass="form-control"/>
				<form:errors path="senha"/>
			</div>
			<div class="form-group">
				<label>Confirmar Senha</label>
				<input type="password" name="confirmarSenha" id="confirmarSenha" class="form-control"/> 
			</div>
			<div class="form-group">
				<label>Tipo de Usuário</label>
				<select name="roles[0].nome" class="form-control" id="roleUser">
					<option value="0" label="Select">Selecione</option>
					<option value="ROLE_ADMIN" label="ADMIN">Admin</option>
					<option value="ROLE_CLIENT" label="CLIENT">Cliente</option>
				</select>
	
			</div>
			<button type="submit" id="salvar" >Salvar</button>
		</form:form>
	</div>
</body>
<script type="text/javascript">
	document.getElementById("salvar").addEventListener('click', function(evt){
		var senha = document.getElementById("senha").value.trim();
		var confirmarSenha = document.getElementById("confirmarSenha").value.trim();
		
		if($('#roleUser').find(":selected").text() ==  'Selecione'){
			alert('Selecione um tipo de Usuário');
			evt.preventDefault();
			return;
		}	
		
		if(senha == ''){
			alert('Senha não pode estar vazio');
			evt.preventDefault();
			return;
		}
		
		if (senha != confirmarSenha){
			alert('Senhas digitadas incompatíveis');
			senha.value =="";
			confirmarSenha.value =="";
			evt.preventDefault();
		}	
	})

</script>
</html>