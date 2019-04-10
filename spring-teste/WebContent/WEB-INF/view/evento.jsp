<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!DOCTYPE html>
<html>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/plentz/jquery-maskmoney/master/dist/jquery.maskMoney.min.js"></script>

<spring:url value="/resources/css/bootstrap.css" var="mainCss" />
<spring:url value="/resources/js/bootstrap.js" var="mainJs" />
<spring:url value="/resources/js/jquery.maskedinput.js" var="mskdt" />

<link href="${mainCss}" rel="stylesheet" />
<script src="${mainJs}"></script>
<script src="${mskdt}"></script>

</head>

<body>
 <!-- colocamos as variaveis aqui, e chamamos no controller, e a var serve para executar a ação e fazer toda ação -->
 <!--  CONFIRMA?? -->
	<spring:url var="action" value='/evento/add' />
	<spring:url var="edit" value='/evento/edit/' />
	<spring:url var="remove" value='/evento/remove/' />


	
		<div class="jumbotron">
			<div Style="padding: 25px;border: 1px solid gray;border-radius: 3px;margin: 10px;" hidden="${erroData}">
				<h3 class="mb-0">Casa de Show reservada neste Dia..</h3>
			</div>
			<form:form action="${action}" modelAttribute="evento">
				<form:hidden path="id" />
				<div class="form-group">

					<label class="col-sm-2 control-label">Nome:</label>
					<form:input path="nome" cssClass="form-control" placeholder="Nome" />

				</div>
				<div class="form-group">

					<label class="col-sm-2 control-label">Data:</label>
					<form:input path="data" id="data" cssClass="form-control" placeholder="" />

				</div>
				
				<div class="form-group">

					<label class="col-sm-2 control-label">Capacidade:</label>
					<form:input path="capacidade" cssClass="form-control" placeholder="Capacidade" />

				</div>
				
				<div class="form-group">
					
					<label class="col-sm-2 control-label">Valor Unitário:</label>
					<form:input path="ingressoUnitario" cssClass="form-control" />
					
				</div>
				
				<label class="col-sm-2 control-label">Local:</label>
					<form:select path="casaShow" cssClass="form-control">
        				<c:forEach items="${casas}" var="c">  
            				<option value="${c.id}">${c.nome }</option>  
        				</c:forEach>
					</form:select>
			
				<input type="submit" value="Submit" class="btn btn-primary" />
			</form:form>
			<br />
			<c:if test="${!empty eventos}">

				<table class="table">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Nome</th>
							<th scope="col">Data</th>
							<th scope="col">Val_Unit</th>
							<th scope="col">Capacidade</th>
							<th scope="col">Local</th>
							<th scope="col">Edit</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="evento" items="${eventos}">
							<tr>
								<th scope="row">${evento.id}</th>
								<td>${evento.nome}</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${evento.data.time}"/></td>
								<td>${evento.ingressoUnitario}</td>
								<td>${evento.capacidade}</td>
								<td>${evento.casaShow.nome}</td>
								<td><a href="${edit}${evento.id}">Edit</a></td>
								<td><a href="${remove}${evento.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>

	<script >
	jQuery("#data").mask("99/99/9999");   
	</script >

</body>

	
</html>