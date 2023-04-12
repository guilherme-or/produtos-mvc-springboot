<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Produtos</title>

<!-- ATALHO PARA TRAZER A URL DE CONTEXTO DO PROJETO -->
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<!-- ATALHOS PARA OS ARQUIVOS ESTATICOS DO WEBJAR -->
<spring:url value="${contextPath}/webjars/bootstrap/5.2.3/css" var="css" />
<spring:url value="${contextPath}/webjars/jquery" var="jquery" />
<spring:url value="${contextPath}/webjars/bootstrap/js" var="js" />

<!-- APONTAMENTO PARA O CSS DO BOOTSTRAP -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- CSS PARA NOSAS CUSTOMIZACOES -->
<link href="/css/style.css" rel="stylesheet">


<!-- LINKS PARA USAR FONTE CUSTOMIZAVEL DO GOOGLE FONTES -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">

</head>

<body>
	<header>
		<%@include file="../navbar/navbar.html"%>
	</header>

	<main class="container">
		<h2 class="fonte-titulo text-danger my-4">Produto</h2>
		<form:form modelAttribute="produtoModel"
			action="${contextPath}/produto/${produtoModel.id}"
			method="put" class="form">

			<spring:hasBindErrors name="produtoModel">
				<div class="alert alert-danger" role="alert">
					<form:errors path="*" class="has-error" />
				</div>
			</spring:hasBindErrors>

			<div class="form-group">
				<form:input path="id" type="hidden" name="id" />
			</div>

			<div class="form-group">
				<label class="control-label" for="nome">Nome</label>
				<form:input path="nome" class="form-control" type="text" name="nome"
					id="nome" />
				<font color="red"> <form:errors path="nome" /></font>
			</div>
			<div class="form-group">
				<label class="control-label" for="idCategoria">Categoria</label>
				<form:select path="categoriaModel.idCategoria" class="form-select"
					name="categoriaModel.idCategoria" id="idCategoria">
					<form:options items="${categoriaModel}" itemValue="idCategoria"
						itemLabel="nomeCategoria" />
				</form:select>
				<font color="red"> <form:errors
						path="categoriaModel.idCategoria" />
				</font>
			</div>
			<div class="form-group">
				<label class="control-label" for="idMarca">Marca</label>
				<form:select path="marcaModel.idMarca" class="form-select"
					name="marcaModel.idMarca" id="idMarca">
					<form:options items="${marcaModel}" itemValue="idMarca"
						itemLabel="nomeMarca" />
				</form:select>
				<font color="red"> <form:errors path="marcaModel.idMarca" />
				</font>
			</div>
			<div class="form-group">
				<label class="control-label" for="sku">SKU</label>
				<form:input path="sku" class="form-control" type="text" name="sku"
					id="sku" />
				<font color="red"> <form:errors path="sku" />
				</font>
			</div>
			<div class="form-group">
				<label class="control-label" for="descricao">Descrição</label>
				<form:textarea path="descricao" class="form-control"
					name="descricao" id="descricao" />
				<font color="red"><form:errors path="descricao" /></font>
			</div>
			<div class="form-group">
				<label class="control-label" for="preco">Preço</label>
				<form:input path="preco" class="form-control" type="number"
					name="preco" id="preco" step=".01" />
				<font color="red"><form:errors path="preco" /></font>
			</div>
			<div class="form-group">
				<label class="control-label" for="caracteristicas">Características</label>
				<form:textarea path="caracteristicas" class="form-control"
					type="text" name="caracteristicas" id="caracteristicas" />
				<font color="red"><form:errors path="caracteristicas" /></font>
			</div>
			<hr>
			<a class="btn btn-secondary" href="${contextPath}/produto">Cancelar</a>
			<button class="btn btn-primary" type="submit">Salvar</button>
		</form:form>
	</main>
	<script src="${jquery}/jquery.min.js"></script>
	<script src="${js}/bootstrap.min.js"></script>
</body>

</html>