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
public enum gender {
   
  MALE(0),
  FEMALE(1),
  TRANSGENDER(2);
	
	private gender(int value)
	{this.value=value;
	}
	private int value;
	public int getValue()
	{
		return value;
	}
	
}
