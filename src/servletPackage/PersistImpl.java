package servletPackage;

import java.sql.Connection;
import java.sql.ResultSet;

public class PersistImpl {
	Connection connection = null;
	
	public PersistImpl(Connection con){
		connection = con;
	}//constructor
	
	public ResultSet getMeteorites() {
		String query =  "select * from landings";
		ResultSet rs = DbAccessImpl.retrieve(connection, query);
		return rs;
	}
}
