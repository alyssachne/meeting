package Entity;

import java.util.ArrayList;

public class Attendee extends User {

    public Attendee(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public boolean signUp(Integer eventId) {
        if(eventList.contains(eventId)) {
            return false;
        }
        eventList.add(eventId);
        return true;
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
