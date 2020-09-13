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
public enum usertype {
    
    USER("user"),
    EDITOR("editor"),
    CHIEF_EDITOR("chiefeditor");
	
	private usertype(String name)
	{
		this.name=name;
		
	}
	private String name;
	public String getName()
	{
		return name;
	}
    
}
