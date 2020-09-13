/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrillio.managers;
import java.util.*;
import thrillio.entities.user;

import thrillio.constants.gender;
import thrillio.constants.usertype;
import thrillio.dao.*;
/**
 *
 * @author siddhi agrawal
 */
public class usermanagers {
    
    private static usermanagers instance= new usermanagers();
    private usermanagers(){}
    public static usermanagers getInstance(){
        return instance;
    }
    private static UserDao dao=new UserDao();
    
    public user createuser(long id,String email,String password,String firstname, String lastname,gender Gender,usertype Usertype){
        user User=new user();
        User.setId(id);
        User.setEmail(email);
        User.setFirstname(firstname);
        User.setLastname(lastname);
        User.setGender(Gender);
        User.setUsertype(Usertype);
        User.setPassword(password);      
        
        return User;
    }
    public List<user> getUsers()
    {
        return dao.getUsers();
    }
    
}
