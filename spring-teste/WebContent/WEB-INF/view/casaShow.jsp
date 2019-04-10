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
	<spring:url var="action" value='/casaShow/add' />
	<spring:url var="edit" value='/casaShow/edit/' />
	<spring:url var="remove" value='/casaShow/remove/' />

		<div class="jumbotron">

			<form:form action="${action}" modelAttribute="casaShow">
				<form:hidden path="id" />
				<div class="form-group">

					<label class="col-sm-2 control-label">Nome:</label>
					<form:input path="nome" cssClass="form-control" placeholder="Nome" />

				</div>
				<div class="form-group">

					<label class="col-sm-2 control-label">Endereço:</label>
					<form:input path="endereco" cssClass="form-control" placeholder="Endereço" />

				</div>
				
				
				<input type="submit" value="Submit" class="btn btn-primary" />
			</form:form>
			<br />
			<c:if test="${!empty casa}">

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nome</th>
							<th scope="col">Endereço</th>
							<th scope="col">Edit</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="casa" items="${casa}">
							<tr>
								<th scope="row">${casa.id}</th>
								<td>${casa.nome}</td>
								<td>${casa.endereco}</td>
								<td><a href="${edit}${casa.id}">Edit</a></td>
								<td><a href="${remove}${casa.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</body>
</html>