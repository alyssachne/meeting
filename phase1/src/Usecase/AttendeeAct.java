package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AttendeeAct implements Usable, Serializable {
    public HashMap<String,Attendee> attendeeMap;

    public AttendeeAct(){
        attendeeMap = new HashMap<>();
    }

    public void createAttendee(String name, String username, String password) {
        Attendee attendee = new Attendee(name, username, password);
        attendeeMap.put(attendee.getUsername(),attendee);
    }

    public boolean login(String username, String password){
        if (password.equals(getAttendee(username).getPassword())){
            return true;
        }
        return false;
    }

    public Attendee getAttendee(String username){
        return attendeeMap.get(username);
    }

    public ArrayList<Integer> getEvents(String username){
        return getAttendee(username).getEvents();
    }

    public void addMessage(String receiver, String sender, String message){
        getAttendee(receiver).addMessage(sender,message);
    }

    public ArrayList<String> getContacts(String username){
        return getAttendee(username).getContacts();
    }

    public ArrayList<String> getMessage(String receiver, String sender){
        return getAttendee(receiver).getMessage(sender);
    }

    @Override
    public boolean signUp(String username, int eventId) {
        return getAttendee(username).signUp(eventId);
    }

    @Override
    public boolean cancelSpot(String username, int eventId) {
        return getAttendee(username).cancelSpot(eventId);
    }

}
