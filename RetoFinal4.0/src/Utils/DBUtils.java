package Utils;


public class DBUtils {


	private static final String SERVER = "localhost";
	private static final String PORT = "3306";
	private static final String DATABASE = "Reto4";

	public static final String URL = "jdbc:mysql://" + SERVER + ":" + PORT +"/" + DATABASE;

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public static final String USER = "root";
	public static final String PASS = "elorrieta";

}