package servletPackage;

public abstract class DbAccessConfig {
	
    /** The JDBC connection string/URL.
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/meteorite";
    /** The database user name.
     */
    static  String DB_CONNECTION_USERNAME = "dbuser";

    /** The password for the database user.
     */
    static  String DB_CONNECTION_PWD = "root";
    
}
