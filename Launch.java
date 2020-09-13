
package thrillio;
import java.util.*;
import thrillio.*;
import thrillio.entities.*;
import thrillio.managers.*;
public class Launch {
    public static List<user> Users;
    public static List<List<bookmark>> Bookmarks;
    
    public static void loadData()
    {  System.out.println("1.Loading data...");
       datastore.loadData();
       
       Users=usermanagers.getInstance().getUsers();
       Bookmarks=bookmarkmanager.getInstance().getBookmarks();
       
       //System.out.println("Printing data...");
      // printUserData();
      // printBookmarkData();
        
    }
    private static void printUserData()
    { for(user User:Users)
        System.out.println(User);
    }
    private static void printBookmarkData()
    {  for(List<bookmark> BookmarkList:Bookmarks)
        for(bookmark Bookmark:BookmarkList)
            System.out.println(Bookmark);
        
    }
    private static void start()
    {
        //System.out.println("2.Bookmarking...");
        for(user User:Users)
            View.browse(User, Bookmarks);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        loadData();
        start();
    }
    
}
