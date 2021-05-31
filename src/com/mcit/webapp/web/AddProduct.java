package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.webapp.db.ProductDAO;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProduct() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);

		request.getRequestDispatcher("add-product.html").include(request, response);

//		response.sendRedirect("add-product.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String productName = request.getParameter("name");
		String productPrice = request.getParameter("price");

		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// insert product table
		try {
			ProductDAO.addProduct(productName, productPrice);
			out.print("<h3 style='color:green'> Product data inserted sucessfully <h3>");
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, Product data insertion failed ! <h3>");
		}
	}

}
