
package thrillio;
import thrillio.entities.*;
import thrillio.partner.Shareable;
import thrillio.constants.usertype;
import thrillio.controllers.*;
import thrillio.constants.*;
import java.util.List;

public class View {
	public static void browse(user User,List<List<bookmark>> bookmarks)
	  {  System.out.println("\n"+User.getEmail()+"is browsing items");
	      int bookmarkCount=0;
	     
	     
	      for(List<bookmark> BookmarkList:bookmarks)
	      { for(bookmark Bookmark:BookmarkList)
	        {  //if(bookmarkCount<datastore.USER_BOOKMARK_LIMIT)
	          boolean isBookmarked=getBookmarkDecision(Bookmark);
	          if(isBookmarked)
	    	  {  bookmarkCount++;
	    	  BookmarkController.getInstance().saveUserBookmark(User,Bookmark);
		         
		         System.out.println("New Item Bookmarked---"+Bookmark);
	    	  }
	          
	    	 
	    	//Mark as Kid Friendly
		      if(User.getUsertype().equals(usertype.CHIEF_EDITOR) || User.getUsertype().equals(usertype.EDITOR))
		      {   
		    	  if(Bookmark.isKidFriendlyEligible() && Bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN))
		    	  {  KidFriendlyStatus kidFriendlyStatus=getKidFriendlyStatusDecision(Bookmark);
		    	      if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN))
		    	      { BookmarkController.getInstance().setKidFriendlyStatus(User,kidFriendlyStatus,Bookmark);
		    	    	 
		    	      }
		    	  }
		    	  //Sharing!!
		    	  if(Bookmark.isKidFriendlyEligible() && Bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
		    			  && Bookmark instanceof Shareable)
		    	  {  boolean isShared=getShareDecision();
		    	   if(isShared)
		    	   {
		    		   BookmarkController.getInstance().share(User,Bookmark);
		    	   }
		    		  
		    	  }
		      }
	          
	        }
	    
	      }
	     
	      
	  }
  private static boolean getShareDecision() {
		// TODO Auto-generated method stub
	  return Math.random()<0.5?true:false;
		
	}
private static KidFriendlyStatus getKidFriendlyStatusDecision(bookmark Bookmark) {
	  //return Math.random()<0.4?KidFriendlyStatus.APPROVED:((Math.random()>=0.4 && Math.random()<0.8)? KidFriendlyStatus.REJECTED: KidFriendlyStatus.UNKNOWN);
		
	  double decision=Math.random();
	  return decision<0.4 ? KidFriendlyStatus.APPROVED:(decision>=0.4 && decision<0.8)? KidFriendlyStatus.REJECTED: KidFriendlyStatus.UNKNOWN;
	}
/*public static void bookmark(user User,bookmark[][] Bookmarks)
  {  System.out.println("\n"+User.getEmail()+"is bookmarking");
    
     for(int i=0;i<datastore.USER_BOOKMARK_LIMIT;i++)
     {
         int typeoffset=(int)(Math.random()*datastore.BOOKAMARK_TYPES_COUNT);
         int bookmarkoffset=(int)(Math.random()*datastore.BOOKMARK_COUNT_PER_TYPE);
         
         bookmark Bookmark=Bookmarks[typeoffset][bookmarkoffset];
         BookmarkController.getInstance().saveUserBookmark(User,Bookmark);
         
         System.out.println(Bookmark);
     }
  }*/

	
	
	private static boolean getBookmarkDecision(bookmark bookmark) {
		// TODO Auto-generated method stub
		return Math.random()<0.5?true:false;
		
	}
}
