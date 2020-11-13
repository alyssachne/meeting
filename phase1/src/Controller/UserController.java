package Controller;

import Entity.Organizer;
import Entity.User;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

public class UserController {
    HashMap<String,User> userMap = new HashMap<>();
    User current;

    public boolean Login(String username, String password){
        User current = userMap.get(username);
        if (current.getPassword().equals(password)){
            this.current = current;
            return true;
        }else{
            return false;
        }
    }

//    public void createOrganizer(){
//        Organizer organizer = new Organizer("admin", "admin", "admin");
//        userMap.put("admin",organizer);
//    }

    public void addUser(User user){
        userMap.put(user.getUsername(),user);
    }



    public String userType(){
        return current.typeGetter();
    }
}
