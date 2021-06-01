package com.mcit.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mcit.webapp.db.DBConnection;
import com.mcit.webapp.db.ProductDAO;

/**
 * Servlet implementation class GetProductAndCount
 */
@WebServlet("/get-product-count")
public class GetProductAndCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductAndCount() { }

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
			CallableStatement cstm = DBConnection.getConnection().prepareCall("{ call get_all_products_and_count() }");
			
			ResultSet rst1 = cstm.executeQuery();
			
			while(rst1.next()) {
				out.println("<p>" + rst1.getInt("id") + " , " + rst1.getString("name") +" , "+
						rst1.getDouble("price") + " , " + rst1.getDate("date_added") +"</p>");
			}
			cstm.getMoreResults();		
			ResultSet rst2 = cstm.getResultSet();
			
			while(rst2.next()) {
				out.println("<p>-----------------------</p>");
				out.println("<p> Total Products : " + rst2.getInt("total_products") + " </p>");
			}
		} catch (SQLException e) {
			out.print(e);
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
