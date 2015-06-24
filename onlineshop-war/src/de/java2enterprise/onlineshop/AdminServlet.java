package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Customer;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	private DataSource ds;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		ResultSet rs_customer;
		ResultSet rs_items;
	try {
			rs_customer = showCustomer();
			rs_items = showItems();
			
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		final RequestDispatcher dispatcher = request
				.getRequestDispatcher("adminResult.jsp");
		request.setAttribute("Customers", rs_customer);
		request.setAttribute("Items", rs_items);
		dispatcher.forward(request, response);
	}

	private ResultSet showCustomer() throws Exception {
		final Connection con = ds.getConnection();
		final Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM onlineshop.customer");
//		while (rs.next()){
//			String email = rs.getString("email");
//			String password = rs.getString("password");
//			System.out.println("Email: "+email+" Passwort: "+password);
//		}
		con.close();
		return rs;
	}
	
	private ResultSet showItems() throws Exception {
		final Connection con = ds.getConnection();
		final Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM onlineshop.item");
//		while (rs.next()){
//			String titel = rs.getString("titel");
//			String description = rs.getString("description");
//			System.out.println("Titel: "+titel+" Description: "+description);
//		}
		con.close();
		return rs;
	}

}
