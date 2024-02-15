package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {
	static Connection connect = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/bazahostel", "root", "123456");
		}catch(Exception e) {System.err.println(e);}
		
		return connect;
	}
	
	public static ObservableList<Users> getDataUsers(){
		 Connection con=DBConnection.getConnection();
		 ObservableList<Users> list = FXCollections.observableArrayList();
		 try  {
		      PreparedStatement pst=con.prepareStatement("SELECT name, password, email, contact FROM user");
		      
		      ResultSet result=pst.executeQuery();
		      
		      while(result.next()) {
		          System.out.println("Name: " + result.getString("name")); // Debug statement
		          list.add(new Users(result.getString("name"), result.getString("password"), result.getString("email"), result.getString("contact")));
		      }
	       } catch (SQLException e1) { e1.printStackTrace(); }
		return list;
	}
}
