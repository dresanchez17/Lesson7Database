import java.io.*;
import java.util.*;
import java.sql.*;
import java.nio.file.*;

/**
 * 
 * @author asanchez
 *
 */
public class Lesson7Database {
	
	private static String queryAll = "SELECT * FROM Lessons";
	
	public static Connection getConnection() throws SQLException, IOException {
		//Object to hold properties
		Properties prop = new Properties();
		
		try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
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
		//
		try (Scanner in = args.length == 0 ? new Scanner(Paths.get("Lesson.sql", "UTF-8")) : new Scanner(Paths.get(args[0], "UTF-8"))) {
			// Connect to database. Create a statement.
			System.out.print("Connectting to the database: ");
			try(Connection conn = getConnection();
					Statement stat = conn.createStatement()) {
				System.out.print("Done.");
				// Execute statements from file.
				
				System.out.println("Populating the database: ");
				while(!in.hasNext()) {
					String line = in.nextLine().trim();
					if (line.endsWith(";")) {
						line = line.substring(0, line.length() - 1);
					}
				}
				System.out.print("Done.");
				System.out.println("Query of Lessons table results:");
				ResultSet resultSet = stat.executeQuery(queryAll);
				
				
				
				
			}
			catch (SQLException ex) {
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
