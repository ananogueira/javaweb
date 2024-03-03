<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%-- SuppressWarnings: suprime o warning da linha ArrayList, porque não consegue verificar o ArrayList na interpretação e na execução --%>
<%
	@ SuppressWarnings ("uncheked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contactos");
%>
<!DOCTYPE html>
<html lang="pt-pt">
<head>
<meta charset="utf-8">
<title>Agenda de Contactos</title>
<link rel="icon" href="images/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contactos</h1>
	<a href="novo.html" class="Botao1">Novo Contacto</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telemóvel</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++) { %>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getTelemovel()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="Botao1">Editar</a>
						<!-- Passo 23.1 -->
						<a href="javascript:confirmar(<%=lista.get(i).getIdcon()%>)" class="Botao2">Apagar</a></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>
