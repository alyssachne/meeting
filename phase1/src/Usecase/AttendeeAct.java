package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AttendeeAct extends Act implements Serializable {
    public HashMap<String,Attendee> attendeeMap;

    public AttendeeAct(){
        attendeeMap = new HashMap<>();
    }

    @Override
    public void createUser(String name, String username, String password) {
            Attendee attendee = new Attendee(name, username, password);
            attendeeMap.put(attendee.getUsername(),attendee);
    }

    @Override
    public User getUser(String username) {
            return attendeeMap.get(username);
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
