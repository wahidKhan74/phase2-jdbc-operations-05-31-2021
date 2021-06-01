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
@WebServlet("/update-product")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProduct() {
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

		request.getRequestDispatcher("update-product.html").include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String productId = request.getParameter("id");
		String productName = request.getParameter("name");
		String productPrice = request.getParameter("price");

		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// insert product table
		try {
			int noOfRowAffected = ProductDAO.updateProduct(productId, productName, productPrice);
			if(noOfRowAffected > 0) {
				out.print("<h3 style='color:green'> Product data updated sucessfully for " +noOfRowAffected +" records<h3>");
			} else {
				out.print("<h3 style='color:red'> Sorry, Product data update failed ! <h3>");
			}
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, Product data update failed ! <h3>");
		}
	}

}
