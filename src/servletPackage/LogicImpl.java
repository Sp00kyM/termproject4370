package servletPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogicImpl {
	HttpServletRequest request=null;
	HttpServletResponse response=null;
	PersistImpl persist = null;
	
	public LogicImpl(HttpServletRequest req, HttpServletResponse res, Connection con){
		request = req;
		response= res;
		persist = new PersistImpl(con);
	} //constructor
	
	public ArrayList<Meteorite> getMets(){
		ArrayList<Meteorite> meteorites = new ArrayList<Meteorite>();
		
		ResultSet rs = persist.getMeteorites();
		
		try {
			while(rs.next()) {
				Meteorite met = new Meteorite();
				met.setLatitude(rs.getDouble("latitude"));
				met.setLongitude(rs.getDouble("longitude"));
				met.setDescription(rs.getString("description"));
				meteorites.add(met);
			}
		}
		catch(SQLException e) {
			System.err.println("Couldn't get the resultset in LogicImpl "+ e.getMessage());
		}
		
		return meteorites;
	}
}
