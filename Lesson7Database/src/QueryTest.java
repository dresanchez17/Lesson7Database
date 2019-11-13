import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.sql.*;

/**
 * This program demonstrates several complex database queries.
 * @author asanchez
 *
 */
public class QueryTest {
	private static final String allQuery = "SELCT Books.Price, Books.Title FROM Books";
	
	private static final String authorPublisherQuery = "SELECT Books.Price, Books.Title"
			+ "FROM Books, BooksAuthors, Authors, Publishers"
			+ "WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
			+ "AND Books.Publisher_Id = Publishers.Publisher_Id AND Authors.Name = ?"
			+ "AND Publishers.Name = ?";
	
	private static final String authorQuery = "SELECT Books.Price, Books.Title "
			+ "FROM Books, BooksAuthors, Authors"
			+ "WHERE Authors.Author.Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
			+ "AND Authors.lName = ?";
	
	

}
