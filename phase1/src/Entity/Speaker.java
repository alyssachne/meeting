package Entity;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Map;


public class Speaker extends User {
    // The first Integer is the started time and the second one is eventId.
    public Map<Integer, Integer> events;

    public Speaker(String name, String username, String password) {
        super(name, username, password);
        for (int i = 9; i < 16; i++) {
            events.put(i, null);
        }
    }

    public boolean giveEvent(Integer eventId, Integer time){
        if(events.get(time) != null) {
            return false;
        }
        events.put(time, eventId);
        return true;
    }

    public Map<Integer, Integer> getEvents() {
        return events;
    }


    public String toString(){
        return "Speaker: " + getUsername();
    }


    @Override
    public String typeGetter(){return "Speaker";};
}
