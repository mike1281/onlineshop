package de.java2enterprise.onlineshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.Customer;
import de.java2enterprise.onlineshop.model.Item;


@WebServlet("/sell")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private DataSource ds;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if (session.getAttribute("customer") == null){
			System.out.println("Nicht eingeloggt.");
			return;
		}
		
//		final String title = req.getParameter("title");
//		final String description = req.getParameter("description");
//		final double price = Double.parseDouble(req.getParameter("price"));
//		final byte[] foto = null; //todo 
//		Customer customer = (Customer)session.getAttribute("customer");
//		final Long seller_id = customer.getId();
				
//		Item item = new Item(description, foto, price, title, null, seller_id);
//		Item item = new Item("Beschreibung", null, null, "Titel", null, null);
		Item item = new Item();

		try {

			persist(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void persist(Item item)
			throws SQLException {
		
		final Connection con = ds.getConnection();
		final Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO onlineshop.item (title) VALUES ("
				+ item.getTitle() + ")");
		con.close();
	}

}
