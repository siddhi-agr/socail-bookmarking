/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrillio.constants;

/**
 *
 * @author siddhi agrawal
 */
public enum bookgenre {
   
     ART ("Art"),
     BIOGRAPHY ( "Biography"),
     CHILDREN ( "Children"),
     FICTION ( "Fiction"),
     HISTORY ( "History"),
     MYSTERY ( "Mystery"),
     PHILOSOPHY ( "Philosophy"),
     RELIGION ( "Religion"),
     ROMANCE ( "Romance"),
     SELF_HELP ( "Self help"),
     TECHNICAL ( "Technical");
	private String name;
    private bookgenre(String name)
    {
    	this.name=name;
    }
    
    
    public String getName()
    {
    	return name;
    }
    
}
