import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class DropTable {
	
	private static String dropLessons = "DROP TABLE Lessons";
	
	public static Connection getConnection() throws SQLException, IOException {
		//Object to hold properties
		Properties prop = new Properties();
		
		try (InputStream in = Files.newInputStream(Paths.get("COREJAVA_database.properties"))) {
			prop.load(in); 
		}
		String drivers = prop.getProperty("jdbc.drivers");
		if (drivers != null) System.setProperty("jdbc.drivers", drivers);
		String url = prop.getProperty("jdbc.url");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) throws IOException {
		
		try (Connection conn = getConnection(); 
				Statement stat = conn.createStatement()) {
			stat.executeUpdate(dropLessons);
			System.out.println("Done");
			
		}
		catch (SQLException ex) {
			for (Throwable t : ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
