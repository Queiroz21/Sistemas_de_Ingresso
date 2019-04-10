<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<spring:url value="/resources/css/bootstrap.css" var="mainCss" />
<spring:url value="/resources/js/bootstrap.js" var="mainJs" />

<link href="${mainCss}" rel="stylesheet" />
<script src="${mainJs}"></script>

</head>

<body>
	<div class="container">
			<a class="navbar-brand" href="student-crud/loginForm">Login</a>
		<div class="jumbotron">

			<form:form action="efetuaLogin" modelAttribute="cliente">
				<div class="form-group">

					<label class="col-sm-2 control-label">Email:</label>
					<form:input path="email" cssClass="form-control" placeholder="Email" />

				</div>
				
				<div class="form-group">

					<label class="col-sm-2 control-label">Senha:</label>
					<form:input path="senha" cssClass="form-control" placeholder="Password" />

				</div>
				<input type="submit" value="Login" class="btn btn-primary" />
				<div class="float-right">
				<label >Não possui um login?<br><a href="cliente"> Cadastre-se aqui</a></label>
				</div>
			</form:form>
			<br />
			</div>
		</div>
	</body>
</html>