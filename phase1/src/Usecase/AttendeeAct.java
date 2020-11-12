package Usecase;

import Entity.Attendee;
import Entity.Event;
import Usecase.EventManager;

import java.util.ArrayList;

public class AttendeeAct {
    public ArrayList<Attendee> allAttendees;
    public AttendeeAct(ArrayList<Attendee> all_attendees){
        this.allAttendees = all_attendees;
    }

    public void createAttendee(String name, String username, String password) {
        Attendee attendee = new Attendee(name, username, password);
        allAttendees.add(attendee);
    }

    public boolean verifyAttendee(String name, String username, String password){
        for (Attendee attendee : allAttendees){
            if (attendee.getName().equals(name) && attendee.getUsername().equals(username) &&
                    attendee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean signUp(Attendee attendee, Event event){
        if (event.getNumOfAttendees() != 2 && !attendee.getEvents().contains(event.getId())){
            attendee.addEvent(event.getId());
            event.addAttendee(attendee.getUsername());
            return true;
        }
        return false;
    }

    public boolean cancel(Attendee attendee, Event event){
        if (attendee.getEvents().contains(event.getId())){
            attendee.removeEvent(event.getId());
            return true;
        }
        return false;
    }

    public ArrayList<String> checkSchedule(Attendee attendee){}

    public void sendMessage(Attendee sender, Attendee receiver, String message){}
}
