package de.java2enterprise.onlineshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.java2enterprise.onlineshop.model.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	private DataSource ds;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String query = request.getParameter("search");
		try {
			List<Item> L = find(query);
			for (int i=0; i < L.size(); i++){
				Item I = L.get(i);
				System.out.println("SearchServlet: Item found: "+I.getTitle()+" "+I.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<Item> find(String s) throws Exception {

		Connection con = ds.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM "
				+ "onlineshop.item WHERE titel like ?");
		stmt.setString(1, (s == null) ? "%" : "%" + s + "%");
		ResultSet rs = stmt.executeQuery();
		List<Item> items = new ArrayList<Item>();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getLong("id"));
			item.setTitle(rs.getString("titel"));
			item.setDescription(rs.getString("DESCRIPTION"));
			// HIER WEITERMACHEN
			items.add(item);
		}
		return items;
	}
}
