package Entity;

import java.util.ArrayList;

public class Attendee extends User {

    public Attendee(String name, String username, String password) {
        super(name, username, password);
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }
    public ArrayList<Integer> getEvents() {
        return eventList;
    }

    public String toString(){
        return "Attendee: " + getUsername();
    }


    @Override
    public String typeGetter(){return "Attendee";};
}
