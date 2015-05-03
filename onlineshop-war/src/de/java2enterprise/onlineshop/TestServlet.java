package de.java2enterprise.onlineshop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/__default")
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		writer.println("<!DOCTYPE html>");
		writer.println("<html><body>");

		Connection con = null;
		//DataSource ds;
		try {

			//ds = (DataSource) InitialContext.doLookup("jdbc/__default");
			con = ds.getConnection();

			if (con.isValid(10)) {
				writer.println("<BR>Connected... :-)!");
			}
			con.close();
			
		} catch (Exception ex) {
			writer.println(ex.getMessage());
		} finally {
			if (con != null){
				try{
					con.close();
				} catch (Exception ex){
					
				}
			}
		}
		writer.println("<BR>TestServlet finished!</body></html>");
		writer.close();
	}
}
