<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
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
 <!-- colocamos as variaveis aqui, e chamamos no controller, e a var serve para executar a ação e fazer toda ação -->
 <!--  CONFIRMA?? -->
	<spring:url var="action" value='/cliente/add' />
	<spring:url var="edit" value='/cliente/edit/' />
	<spring:url var="remove" value='/cliente/remove/' />

		<div class="jumbotron">

			<form:form action="${action}" modelAttribute="cliente">
				<form:hidden path="id" />
				<div class="form-group">

					<label class="col-sm-2 control-label">Nome:</label>
					<form:input path="nome" cssClass="form-control" placeholder="Nome" />

				</div>
				<div class="form-group">

					<label class="col-sm-2 control-label">Email:</label>
					<form:input path="email" cssClass="form-control" placeholder="Email" />

				</div>
				
				<div class="form-group">

					<label class="col-sm-2 control-label">Senha:</label>
					<form:input path="senha" cssClass="form-control" placeholder="Password" />

				</div>
				<input type="submit" value="Submit" class="btn btn-primary" />
			</form:form>
			<br />
			<c:if test="${!empty clientes}">

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nome</th>
							<th scope="col">Email</th>
							<th scope="col">Senha</th>
							<th scope="col">Edit</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cliente" items="${clientes}">
							<tr>
								<th scope="row">${cliente.id}</th>
								<td>${cliente.nome}</td>
								<td>${cliente.email}</td>
								<td>${cliente.senha}</td>
								<td><a href="${edit}${cliente.id}">Edit</a></td>
								<td><a href="${remove}${cliente.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</body>
</html>