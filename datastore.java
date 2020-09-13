/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrillio;
import java.util.*;
import java.sql.*;
import java.sql.Date;

import thrillio.constants.bookgenre;
import thrillio.constants.gender;
import thrillio.constants.moviegenre;
import thrillio.constants.usertype;
import thrillio.entities.*;
import thrillio.managers.*;
/**
 *
 * @author siddhi agrawal
 */
public class datastore {
  
	
	private static List<user> users = new ArrayList<>();
	public static List<user> getUsers() {
		return users;
	}

	private static List<List<bookmark>> bookmarks = new ArrayList<>();
	public static List<List<bookmark>> getBookmarks() {
		return bookmarks;
	}
	

	private static List<userbookmark> userBookmarks = new ArrayList<>();
   
    public static void loadData()
    {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// Connection string: <protocol>:<sub-protocol>:<data-source details>
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			loadUsers(stmt);    
			loadWebLinks(stmt); 
			loadMovies(stmt);
			loadBooks(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
 
    private static void loadUsers(Statement stmt) throws SQLException
    {
    	String query="Select * from User";
    	ResultSet rs = stmt.executeQuery(query);
    	while (rs.next()) {
			long id = rs.getLong("id");
			String email= rs.getString("email");
			String password= rs.getString("password");
			String first_name= rs.getString("first_name");
			String last_name= rs.getString("last_name");
			int gender_id = rs.getInt("gender_id");
			gender Gender=gender.values()[gender_id];
			int user_type_id=rs.getInt("user_type_id");
			usertype Usertype=usertype.values()[user_type_id];
			
			user User=usermanagers.getInstance().createuser(id,email,password,first_name,last_name,Gender,Usertype);
			users.add(User);
    	}
    	
    }
	
    private static void loadWebLinks(Statement stmt) throws SQLException
    {
    	String query="Select * from WebLink";
    	ResultSet rs = stmt.executeQuery(query);
    	List<bookmark> bookmarklist=new ArrayList<>();
    	while(rs.next())
    	{
    		long id=rs.getLong("id");
    		String title= rs.getString("title");
			String url= rs.getString("url");
			String host= rs.getString("host");
			bookmark bookmark = bookmarkmanager.getInstance().setweblink(id, title," ", url, host);
			bookmarklist.add(bookmark);
    	}
    	bookmarks.add(bookmarklist);
    }
	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				      + "m.id = md.movie_id and md.director_id = d.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);
		
		List<bookmark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");			
			int genre_id = rs.getInt("movie_genre_id");
			moviegenre genre = moviegenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			
			bookmark bookmark = bookmarkmanager.getInstance().setmovie(id, title,releaseYear, cast, directors, genre, imdbRating," ");
    		bookmarkList.add(bookmark); 
		}
		bookmarks.add(bookmarkList);
	}
	
	private static void loadBooks(Statement stmt) throws SQLException {		    	
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
    	ResultSet rs = stmt.executeQuery(query);
		
    	List<bookmark> bookmarkList = new ArrayList<>();
    	while (rs.next()) {
    		long id = rs.getLong("id");
			String title = rs.getString("title");
			int publicationYear = rs.getInt("publication_Year");
			String publisher = rs.getString("name");		
			String[] authors = rs.getString("authors").split(",");			
			int genre_id = rs.getInt("book_genre_id");
			bookgenre genre = bookgenre.values()[genre_id];
			double rating = rs.getDouble("amazon_rating");
			
			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timeStamp = rs.getTimestamp(8);
			System.out.println("timeStamp: " + timeStamp);
			System.out.println("localDateTime: " + timeStamp.toLocalDateTime());
			
			System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", Rating: " + rating);
    		
    		bookmark bookmark = bookmarkmanager.getInstance().setbook(id, title, publicationYear, publisher, authors, genre, rating," ");
    		bookmarkList.add(bookmark); 
    	}
    	bookmarks.add(bookmarkList);
    }	
	
	public static void add(userbookmark UserBookmark) {
		userBookmarks.add(UserBookmark);		
	}	
}