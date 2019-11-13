import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class TestDB {
	public static void main(String[] args) throws IOException {
		try {
			runTest();
		}
		catch (SQLException ex) {
			for (Throwable t : ex) {
				t.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public static void runTest() throws SQLException, IOException {
		
		try (Connection conn = getConnection();
				Statement stat = conn.createStatement()) {
			stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
			stat.executeUpdate("INSERT INTO Greetings VALUES('Hello, World')");
			
			
			try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")){
				if (result.next()) {
					System.out.println(result.getString(1));
				}
				stat.executeUpdate("DROP TABLE Greetings");
			}
		}
	}
	
	/**
	 * This guy connects to database.
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Connection getConnection() throws SQLException, IOException {
		
		Properties props = new Properties();
		// 
		try (InputStream in = Files.newInputStream(Paths.get("COREJAVA_database.properties"))) {
			// Loads all properties from input stream.
			props.load(in);
		}
		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) System.setProperty("jdbc.drivers", drivers);
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(url, username, password);
	}
}
