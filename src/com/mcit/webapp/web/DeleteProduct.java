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
@WebServlet("/delete-product")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProduct() {
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

		request.getRequestDispatcher("delete-product.html").include(request, response);

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
		
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// insert product table
		try {
			int noOfRowAffected = ProductDAO.deleteProduct(productId);
			if(noOfRowAffected > 0) {
				out.print("<h3 style='color:green'> Product data deleted sucessfully for " +noOfRowAffected +" records<h3>");
			} else {
				out.print("<h3 style='color:red'> Sorry, Product data delete failed ! <h3>");
			}
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, Product data delete failed ! <h3>");
		}
	}

}
