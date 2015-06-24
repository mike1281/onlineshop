<%@ include file="header.jspf"%>
<form action="admin" method="post">
	<legend>Administrierungsoberfläche</legend>
	<input type="submit" value="Zeige Kunden und Items">
</form>

<!-- Liste der Kunden mittels JSTL -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Customer</h1>
<sql:query var="result_customer" sql="select EMAIL, PASSWORD from onlineshop.customer" dataSource="jdbc/__default" />
<c:forEach var="row" items="${result_customer.rows}">
	<c:out value="${row.EMAIL}" />
	<c:out value="${row.PASSWORD}" />
	<br />
</c:forEach>

<h1>Items</h1>
<sql:query var="result_items" sql="select * from onlineshop.item" dataSource="jdbc/__default" />
<c:forEach var="row" items="${result_items.rows}">
	<c:out value="${row.TITEL}" />
	<c:out value="${row.DESCRIPTION}" />
	<br />
</c:forEach>




<%@ include file="footer.jspf"%>