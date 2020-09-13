
package thrillio.controllers;
import thrillio.constants.KidFriendlyStatus;
import thrillio.entities.*;
import thrillio.managers.bookmarkmanager;

public class BookmarkController {
    private static BookmarkController instance=new BookmarkController();
    private BookmarkController()
    {
    }
    public static BookmarkController getInstance()
    {return instance;}
    public void saveUserBookmark(user User,bookmark Bookmark)
    {  bookmarkmanager.getInstance().saveUserBookmark(User,Bookmark);
        
    }
	public void setKidFriendlyStatus(user User, KidFriendlyStatus kidFriendlyStatus, bookmark Bookmark) {
		// TODO Auto-generated method stub
		bookmarkmanager.getInstance().setKidFriendlyStatus(User,kidFriendlyStatus,Bookmark);
	}
	public void share(user User, bookmark Bookmark) {
		// TODO Auto-generated method stub
		bookmarkmanager.getInstance().share(User,Bookmark);
	}
}
