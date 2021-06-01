package com.mcit.webapp.db;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// create table
	public static void createTable() throws ClassNotFoundException, SQLException {

		// db connection & create statement
		Statement stm = DBConnection.getConnection().createStatement();

		// prepare e_product create table
		String query = "create table e_product (id bigint primary key auto_increment, name varchar(100) , price decimal(10,2) , date_added timestamp default now())";

		// Execute query.
		stm.execute(query);

	}

	public static void addProduct(String productName, String productPrice) throws SQLException {

		System.out.println("prdoct : -> " + productName);
		// db connection & create statement
		Statement stm = DBConnection.getConnection().createStatement();

		// create insert product query
		String query = "insert into e_product ( name, price ) values ( +'" + productName + "'," + productPrice + ")";

		stm.execute(query);

	}

	public static ResultSet readProducts() throws SQLException {
		Statement stm = DBConnection.getConnection().createStatement();
		// select query
		String selectQuery = "select * from e_product";
		ResultSet response = stm.executeQuery(selectQuery);
		return response;
	}

	// dynamic parameterized query
	public static int updateProduct(String id, String name, String price) throws SQLException {

		// 1. create a query
		String updateQuery = "update e_product set name=?, price=? where id=?";

		// 2. create prepared statement
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(updateQuery);

		// 3. set parameters
		pstm.setString(1, name);
		pstm.setDouble(2, Double.parseDouble(price));
		pstm.setInt(3, Integer.parseInt(id));

		// 4. execute query
		int noOfRowAffedted = pstm.executeUpdate();

		return noOfRowAffedted;
	}

	public static int deleteProduct(String id) throws SQLException {

		// 1. create a query
		String deleteQuery = "delete from e_product where id=?";

		// 2. create prepared statement
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(deleteQuery);

		// 3. set parameters
		pstm.setInt(1, Integer.parseInt(id));

		// 4. execute query
		int noOfRowAffedted = pstm.executeUpdate();
		
		return noOfRowAffedted;
	}
	
	public static List<ResultSet> getAllProductsAndCount() throws SQLException {
		
		List<ResultSet> list = new ArrayList<>();
		CallableStatement cstm = DBConnection.getConnection().prepareCall("{ call get_all_products_and_count() }");
		
		ResultSet rst1 = cstm.executeQuery();
		list.add(rst1);
		
		cstm.getMoreResults();		
		ResultSet rst2 = cstm.getResultSet();
		list.add(rst2);
		return list ; 
	}
}
