package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mcit.webapp.db.DBConnection;

/**
 * Servlet implementation class InitializeDBConnection
 */
@WebServlet("/init-connection")
public class InitializeDBConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializeDBConnection() {
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

		// db connection
		try {
			DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306/mcit_database", "root", "root");
			if (conn != null) {
				out.print("<h3 style='color:green'> Your DB connection successfully initiated !<h3>");
			} 
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, DB connection failed ! <h3>");
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
