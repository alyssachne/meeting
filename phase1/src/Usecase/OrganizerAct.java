package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OrganizerAct extends Act implements Serializable {

    HashMap<String,Organizer> organizerMap;

    public OrganizerAct(){
        organizerMap = new HashMap<>();
    }

    @Override
    public void createUser(String name, String username, String password) {
        Organizer organizer = new Organizer(name,username,password);
        organizerMap.put(organizer.getUsername(),organizer);
    }

    @Override
    public User getUser(String username) {
        return organizerMap.get(username);
    }

    @Override
    public boolean signUp(String username, Integer eventId) {
        if(getUser(username).getSignUp().contains(eventId)) {
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

}
