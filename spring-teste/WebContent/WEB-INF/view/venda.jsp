<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<spring:url var="action" value='/venda/add' />

	
		<div class="jumbotron">
			<form:form action="${action}" modelAttribute="venda">
				<div align="center">
					<h4>Sr(a) ${usuarioLogado.nome} <br> está é sua escolha</h4>
					<h5 >Nome do evento: ${venda.evento.nome}</h5>
					<h5 ><b>Data do evento: <fmt:formatDate pattern="dd/MM/yyyy" value="${venda.evento.data.time}"/></b></h5>
					<h5><em>Valor: ${venda.evento.ingressoUnitario}</em></h5>
					
				</div>
			
			
				<form:hidden path="id" />
					<form:input path="evento" hidden="True" value="${venda.evento.id}" />
					<form:input path="cliente" hidden="True" value="${usuarioLogado.id}" />
				<div class="form-group">
					
					<label class="col-sm-2 control-label">Quantidade de Ingressos:</label>
					<form:input path="quantidadeIngressos" cssClass="form-control" />

				</div>
				
				<!-- <div class="form-group">    #N NECESSITA, O PROGRAMA FAZ O CALCULO
					<label class="col-sm-2 control-label">Total da compra:</label>
					<form:input path="total" cssClass="form-control" placeholder="" />
				</div> -->
				<input type="submit" value="Submit" class="btn btn-primary" />
			</form:form>
			<br />
		</div>
	</body>
</html>