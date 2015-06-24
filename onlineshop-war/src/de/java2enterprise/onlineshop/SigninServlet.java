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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Customer;


@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	private DataSource ds;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		final String email = request.getParameter("email");
		final String password = request.getParameter("password");

		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);

		boolean found = false;
		
		try {
			found = find(customer);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
//			final RequestDispatcher dispatcher = request
//					.getRequestDispatcher("notRegistered.jsp");
//			dispatcher.forward(request, response);
//			return;
		}
		final RequestDispatcher dispatcher;
		
		if (found){
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			
			dispatcher = request
				.getRequestDispatcher("index.jsp");
		}else{
			dispatcher = request
					.getRequestDispatcher("notRegistered.jsp");
		}
		dispatcher.forward(request, response);
	}

	private boolean find(Customer customer) throws SQLException {
		final Connection con = ds.getConnection();
		final Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM onlineshop.customer "
				+ "WHERE email='"+customer.getEmail()+"' AND "
						+ "password='"+customer.getPassword()+"'");
		con.close();
		if (rs.next()) return true;
		else return false;

	}

}
