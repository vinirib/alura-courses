<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<style type="text/css">
	body {
	  padding-top: 40px;
	  padding-bottom: 40px;
	  background-color: #eee;
	}
	
	.form-signin {
	  max-width: 330px;
	  padding: 15px;
	  margin: 0 auto;
	}
	.form-signin .form-signin-heading,
	.form-signin .checkbox {
	  margin-bottom: 10px;
	}
	.form-signin .checkbox {
	  font-weight: normal;
	}
	.form-signin .form-control {
	  position: relative;
	  height: auto;
	  -webkit-box-sizing: border-box;
	     -moz-box-sizing: border-box;
	          box-sizing: border-box;
	  padding: 10px;
	  font-size: 16px;
	}
	.form-signin .form-control:focus {
	  z-index: 2;
	}
	.form-signin input[type="email"] {
	  margin-bottom: -1px;
	  border-bottom-right-radius: 0;
	  border-bottom-left-radius: 0;
	}
	.form-signin input[type="password"] {
	  margin-bottom: 10px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}

</style>
</head>
<body>
	<div class="container">
	<form:form servletRelativeAction="/login" method="post" cssClass="form-signin">
		<h2 class="form-signin-heading">Casa do Código</h2>
		<label for="inputEmail" class="sr-only">Email address</label>
		 	<input	id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" type="email" name="username"> 
		<label for="inputPassword" class="sr-only">Password</label> 
			<input id="inputPassword" class="form-control" placeholder="Password" required="" type="password" name="password">
		<c:if test="${param.error != null}">
		    <div id="error">
		    	<font color="red">
			        <s:message code="message.badCredentials">   
			        </s:message>
		        </font>
		    </div>
		</c:if>
		<div class="checkbox">
			<label> <input value="remember-me" type="checkbox">
				Remember me
			</label>
				<label for="criarConta">
					<a href="${s:mvcUrl('LC#criar').build()}">Criar Conta</a>
				</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
	</form:form>

</div>
	
</body>
</html>