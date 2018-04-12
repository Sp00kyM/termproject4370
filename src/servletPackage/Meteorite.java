package servletPackage;

public class Meteorite {
	double longitude;
	double latitude;
	String description = "";
	
	public void setLatitude(double lat) {
		latitude = lat;
	}
	
	public void setLongitude(double longi) {
		longitude = longi;
	}
	
	public void setDescription(String desc) {
		description = desc;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getDescription() {
		return description;
	}
}
