/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrillio.dao;
import java.util.*;
import thrillio.datastore;
import thrillio.entities.*;
public class UserDao {
   public List<user> getUsers()
   {
       return datastore.getUsers();
   }
}
