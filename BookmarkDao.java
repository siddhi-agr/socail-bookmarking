
package thrillio.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import thrillio.datastore;
import thrillio.entities.*;
public class BookmarkDao {
    public List<List<bookmark>> getBookmarks()
    {
        return datastore.getBookmarks();
    }
    public void saveUserBookmark(userbookmark Userbookmark)
    {
        //datastore.add(Userbookmark);
    	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			if(Userbookmark.getBookmark() instanceof book)
				saveUserBook(Userbookmark,stmt);
			else if(Userbookmark.getBookmark() instanceof movie)
				saveUserMovie(Userbookmark,stmt);
			else
			{
				saveUserWebLink(Userbookmark,stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	private void saveUserWebLink(userbookmark Userbookmark, Statement stmt) throws SQLException {
		String query="insert into User_WebLink (user_id,weblink_id) values("+ 
				  Userbookmark.getUser().getId()+","+Userbookmark.getBookmark().getId()+")";
				stmt.executeUpdate(query);
		
	}
	private void saveUserMovie(userbookmark Userbookmark, Statement stmt) throws SQLException {
		String query="insert into User_Movie (user_id,movie_id) values("+ 
				  Userbookmark.getUser().getId()+","+Userbookmark.getBookmark().getId()+")";
				stmt.executeUpdate(query);
		
	}
	private void saveUserBook(userbookmark Userbookmark, Statement stmt) throws SQLException {
		// TODO Auto-generated method stub
		String query="insert into User_Book (user_id,book_id) values("+ 
		  Userbookmark.getUser().getId()+","+Userbookmark.getBookmark().getId()+")";
		stmt.executeUpdate(query);
	}
	public void updateKidFriendlyStatus(bookmark Bookmark) {
		int kidFriendlyStatus=Bookmark.getKidFriendlyStatus().ordinal();
		long userId=Bookmark.getKidFriendlyMarkedBy().getId();
		
		String tableToUpdate="Book";
		if(Bookmark instanceof movie)
			tableToUpdate="Movie";
		else if(Bookmark instanceof weblink)
			tableToUpdate="WebLink";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			String query="update"+tableToUpdate+ "set kid_friendly_status ="+kidFriendlyStatus+",kid_friendly_marked_by ="+userId +"where id="+Bookmark.getId();
			System.out.println("query(updateKidFriendlyStatus):"+query);
			stmt.executeUpdate(query);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	public void sharedByInfo(bookmark Bookmark) {
		// TODO Auto-generated method stub
     long userId=Bookmark.getSharedBy().getId();
		
		String tableToUpdate="Book";
		 if(Bookmark instanceof weblink)
			tableToUpdate="WebLink";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			String query="update"+tableToUpdate+ "set shared_by ="+userId+"where id="+Bookmark.getId();
			System.out.println("query(updateSharedByStatus):"+query);
			stmt.executeUpdate(query);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
}
