package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.webapp.db.ProductDAO;

/**
 * Servlet implementation class CreateProductTable
 */
@WebServlet("/create-product-table")
public class CreateProductTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProductTable() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// create product table
		try {
			ProductDAO.createTable();
			out.print("<h3 style='color:green'> Product table is created sucessfully <h3>");
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, Product table creation failed ! <h3>");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
