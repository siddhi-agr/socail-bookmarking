
package thrillio.managers;
import thrillio.entities.*;

import java.util.List;

import thrillio.constants.KidFriendlyStatus;
import thrillio.constants.bookgenre;
import thrillio.constants.moviegenre;
import thrillio.dao.*;

public class bookmarkmanager {
    private static bookmarkmanager instance=new bookmarkmanager();
    private bookmarkmanager(){}
    public static BookmarkDao dao=new BookmarkDao();
    public static bookmarkmanager getInstance()
    {
        return instance;
    }
    
    public movie setmovie(long id,String title,int releaseyear,String[] cast,String[] directors,moviegenre genre,double imdbRating,String profileurl)
    {    movie Movie=new movie();
         Movie.setId(id);
         Movie.setCast(cast);
         Movie.setDirectors(directors);
         Movie.setGenre(genre);
         Movie.setImdbRating(imdbRating);
         Movie.setProfileurl(profileurl);
         Movie.setReleaseyear(releaseyear);
         Movie.setTitle(title);
        
         return Movie;
    }
    
    public book setbook(long id,String title,int publicationYear,String publisher,String[] authors,bookgenre genre,double rating,String profileurl)
    {   book Book=new book();
        Book.setId(id);
        Book.setAuthors(authors);
        Book.setGenre(genre);
        Book.setProfileurl(profileurl);
        Book.setPublicationYear(publicationYear);
        Book.setPublisher(publisher);
        Book.setRating(rating);
        Book.setTitle(title);
        
        return Book;
    }
    
    public weblink setweblink(long id,String title,String profileurl,String url,String host)
    {
        weblink Weblink=new weblink();
        Weblink.setId(id);
        Weblink.setTitle(title);
        Weblink.setProfileurl(profileurl);
        Weblink.setHost(host);
        Weblink.setUrl(url);
        
        return Weblink;
        
    }
    public List<List<bookmark>> getBookmarks()
    {
        return dao.getBookmarks();
    }
   public void saveUserBookmark(user User,bookmark Bookmark)
   {
       userbookmark Userbookmark=new userbookmark();
       Userbookmark.setUser(User);
       Userbookmark.setBookmark(Bookmark);
       
       dao.saveUserBookmark(Userbookmark);
   }

public void setKidFriendlyStatus(user User, KidFriendlyStatus kidFriendlyStatus, bookmark Bookmark) {
	// TODO Auto-generated method stub
	Bookmark.setKidFriendlyStatus(kidFriendlyStatus);
	Bookmark.setKidFriendlyMarkedBy(User);
	
	dao.updateKidFriendlyStatus(Bookmark);
	System.out.println("Kid-friendly status:"+kidFriendlyStatus+",Marked by: "+User.getEmail()+", "+Bookmark);
}

public void share(user User, bookmark Bookmark) {
	// TODO Auto-generated method stub
	Bookmark.setSharedBy(User);
	System.out.println("Data to be shared: ");
	if(Bookmark instanceof book)
		System.out.println(((book)Bookmark).getItemData());
	else if(Bookmark instanceof weblink)
		System.out.println(((weblink)Bookmark).getItemData());
	
	dao.sharedByInfo(Bookmark);
}
}
