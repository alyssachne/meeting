package Usecase;

import Entity.Organizer;
import Entity.User;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.HashMap;

public class UserOrganizer {
    HashMap<String,User> userMap = new HashMap<>();

    public boolean Login(String username, String password){
        User current = userMap.get(username);
        if (current.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public void createOrganizer(){
        Organizer organizer = new Organizer("admin", "admin", "admin");
        userMap.put("admin",organizer);
    }
}
