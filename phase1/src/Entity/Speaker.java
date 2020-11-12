package Entity;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Speaker extends User {
    // The first Integer is the started time and the second one is eventId.
    public HashMap<Integer, Integer> events = new HashMap<>(8);

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

    public boolean cancelEvent(Integer eventId) {
        Set<Integer> temp = events.keySet();
        for(Integer time: temp) {
            if(events.get(time).equals(eventId)) {
                events.replace(time, null);
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, Integer> getEvents() {
        return events;
    }

    public ArrayList<Integer> available() {
        ArrayList<Integer> acc = new ArrayList<>();
        Set<Integer> temp = events.keySet();
        for (Integer time: temp) {
            if(events.get(time) == null) {
                acc.add(time);
            }
        }
        return acc;
    }


    public String toString(){
        return "Speaker: " + getUsername();
    }


    @Override
    public String typeGetter(){return "Speaker";};
}
