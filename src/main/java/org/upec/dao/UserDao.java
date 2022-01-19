package org.upec.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	//private static final String SERIALIZED_FILE_NAME ="C:\\Users\\ASUS\\eclipse-workspace\\FirstServlet\\src\\users.xml";
	private static final String SERIALIZED_FILE_NAME = "C:/Users/ASUS/eclipse-workspace/FirstServlet/users.xml";
	public List<User> getAllUsers(){
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			List<User> uList=(List<User>)decoder.readObject();
			//System.out.println(uList);
			return uList;
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found");
			
			//Create and save an empty list
			List<User> l = new ArrayList<User>();
			this.saveUserListToXml(l);
			return l;
		}	
		
	} 
	
   private void saveUserListToXml(List<User> userList) {
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(userList);
		
		encoder.close();
   }
   
   public User getUser(long id){
      List<User> users = getAllUsers();

      
      for(User user: users){
    	  //System.out.println(user.getId() + " == " + id);
         if(user.getId() == id){
            return user;
         }
      }
      return null;
   }

   public long addUser(User pUser){
      List<User> userList = getAllUsers();
      System.out.println(userList.size());
      //System.out.println(pUser.getId());
      
      boolean userExists = false;
      for(User user: userList){
         if(user.getId() == pUser.getId()){
            userExists = true;
            break;
         }
      }		
      if(!userExists){
         userList.add(pUser);
         saveUserListToXml(userList);
         return 1;
      }
      return 0;
   }

   public long updateUser(User pUser){
      List<User> userList = getAllUsers();

      for(User user: userList){
         if(user.getId() == pUser.getId()){
            int index = userList.indexOf(user);			
            userList.set(index, pUser);
            saveUserListToXml(userList);
            return 1;
         }
      }		
      return 0;
   }

   public long deleteUser(long id){
      List<User> userList = getAllUsers();

      for(User user: userList){
         if(user.getId() == id){
            int index = userList.indexOf(user);			
            userList.remove(index);
            saveUserListToXml(userList);
            return 1;   
         }
      }		
      return 0;
   }

   /*public static void main(String argv[]) {
	   UserDao ud = new UserDao();
	   List<User> uList = ud.getAllUsers();
	   
	   for(int i = 0; i < uList.size(); i++) {
		   User u = uList.get(i);
		   
		   System.out.println(u.getName());
	   }
   }*/
}