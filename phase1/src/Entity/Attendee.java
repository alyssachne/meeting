package Entity;

import java.util.ArrayList;
import java.util.Calendar;

public class Attendee extends User {
    public ArrayList<String> contacts;
    public ArrayList<Integer> events;

    public Attendee(String name, String username, String password) {
        super(name, username, password);
        this.contacts = new ArrayList<String>();
        this.events = new ArrayList<Integer>();
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }
    public ArrayList<Integer> getEvents() {
        return events;
    }

    public boolean addContact(String contactUsername){
        if (!this.contacts.contains(contactUsername)){
            this.contacts.add(contactUsername);
            return true;
        }
        return false;
    }

    public boolean removeContact(String contactUsername){
        if (this.contacts.contains(contactUsername)){
            this.contacts.remove(contactUsername);
            return true;
        }
        return false;
    }

    public boolean addEvent(Integer eventId){
        if (!events.contains(eventId)){
            events.add(eventId);
            return true;
        }
        return false;
    }

    public boolean removeEvent(Integer eventId){
        if (events.contains(eventId)){
            events.remove(eventId);
            return true;
        }
        return false;
    }

    public String toString(){
        return "Attendee" + ":" + " " + getUsername();
    }


    @Override
    public String typeGetter(){return "Attendee";};
}
