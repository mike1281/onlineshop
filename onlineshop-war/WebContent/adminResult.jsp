<%@ include file="header.jspf"%>

<legend>Registrierte Kunden und Items</legend>
<%@ page import="java.sql.ResultSet"%>
<%
 	ResultSet rs = (ResultSet) request.getAttribute("Customers");
 	while (rs.next()) {
		String email = rs.getString("email");
		String password = rs.getString("password"); 
		out.println("Email: " + email + "  Passwort: " + password+"<br>"); 
	} 
	
	rs = (ResultSet) request.getAttribute("Items");
 	while (rs.next()) {
		String title = rs.getString("titel");
		String description = rs.getString("description"); 
		out.println("Titel: " + title + "  Description: " + description+"<br>"); 
	}  
	
%>

<%@ include file="footer.jspf"%>