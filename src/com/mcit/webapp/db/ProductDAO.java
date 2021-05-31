package com.mcit.webapp.db;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {

	// creatye table
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
		String query = "insert into e_product ( name, price ) values ( +'"+productName+"'," + productPrice+")";
		
		stm.execute(query);
		
	}
}
