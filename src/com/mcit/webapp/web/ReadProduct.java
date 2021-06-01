package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.webapp.db.ProductDAO;

/**
 * Servlet implementation class ReadProduct
 */
@WebServlet("/read-product")
public class ReadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadProduct() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// execute read products
		try {
			ResultSet rst = ProductDAO.readProducts();
			while(rst.next()) {
				out.println("<p>" + rst.getInt("id") + " , " + rst.getString("name") +" , "+
						rst.getDouble("price") + " , " + rst.getDate("date_added") +"</p>");
			}
			
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Products read failed ! <h3>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
