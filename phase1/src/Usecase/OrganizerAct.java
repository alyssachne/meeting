package Usecase;
import Entity.*;
import org.omg.CORBA.Any;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OrganizerAct implements Usable, Serializable {

    HashMap<String,Organizer> organizerMap;

    public OrganizerAct(){
        organizerMap = new HashMap<>();
    }

    public void createOrganizer(String name, String username, String password){
        Organizer organizer = new Organizer(name,username,password);
        organizerMap.put(organizer.getUsername(),organizer);
    }

    public boolean login(String username, String password){
        if (password.equals(getOrganizer(username).getPassword())){
            return true;
        }
        return false;
    }

    public void addMessage(String receiver, String sender, String message){
        getOrganizer(receiver).addMessage(sender,message);
    }

    public ArrayList<String> getContacts(String username){
        return getOrganizer(username).getContacts();
    }

    public ArrayList<String> getMessage(String receiver, String sender){
        return getOrganizer(receiver).getMessage(sender);
    }

    public Organizer getOrganizer(String username){
        return organizerMap.get(username);
    }

    @Override
    public boolean signUp(String username, int eventId) {
        return getOrganizer(username).signUp(eventId);
    }

    @Override
    public boolean cancelSpot(String username, int eventId) {
        return getOrganizer(username).cancelSpot(eventId);
    }


}
