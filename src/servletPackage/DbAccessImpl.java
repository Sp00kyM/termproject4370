package servletPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfig {
	public static Connection connect() throws ClassNotFoundException {
		Connection con = null;
		
		try {
			// establish a connection
			con = DriverManager.getConnection(DB_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PWD);
			System.out.println("Connected!");
		} 
		catch (SQLException e) {
			//e.printStackTrace();
			System.err.println(e);
		}
		
		return con;
	
	}//connect
	
	public static ResultSet retrieve(Connection con, String query) {
		ResultSet rset = null;
		
		try {
			// create a JDBC statement
			Statement stm = con.createStatement();
			
			// execute a query
			rset = stm.executeQuery(query);
			return rset;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return rset;
		}
	}

	
	public static int create(Connection con, String query) {
		int affected=0;
		try {
			Statement stmt = con.createStatement();
			affected = stmt.executeUpdate(query);
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return affected;
		
	}

	
	public static int update(Connection con, String query) {
		Statement stmt;
		int affected =0;
		try {
			stmt = con.createStatement();
			affected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return affected;
	}

	
	public static int delete(Connection con, String query) {
		
		Statement stmt;
		int rows=0;
		try {
			stmt = con.createStatement();
			rows= stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

	
	public static void disconnect(Connection con) {
		if(con !=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if
		
	}// disconnect
}//DbAccessConfig
