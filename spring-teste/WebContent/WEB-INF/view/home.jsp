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
	<spring:url var="action" value='home/form/' />
	
		<div class="jumbotron">
			<h3 align="center">Bem vindo(a) a maior bilheteria de ingressos do Brasil! </h3>
			<h4 align="center">${usuarioLogado.nome}<br>esperamos que aproveite sua visita!</h4>
			<h4 align="center"><em>O melhor você encontra aqui.</em></h4><br><br>
			<c:forEach items="${eventos}" var="e">
			
				<form action="${action}${e.id} ">
					<div align="center" style="border-top-width: 3px; border-top-style: solid; border-top-color: #000; border-top: 3px solid #000; padding: 10px; margin: 10px;">
						<h5 >Nome: ${e.nome}</h5><br>
						<h6 >Vagas Disponiveís: <b>${e.capacidade}</b></h6>
						<h6 >Valor: R$ ${e.ingressoUnitario}</h6><br>
						<h6 >Data: <b>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${e.data.time}"/>
						</b></h6>
						
						<h6 >Local: <em>${e.casaShow.nome}</em></h6><br>
						<input type="submit" value="Comprar" class="btn btn-primary" />
					</div>
				</form>
			</c:forEach>
		</div>
	</body>
</html>