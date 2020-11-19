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

    public ArrayList<Integer> getAvailable() {
        ArrayList<Integer> acc = new ArrayList<>();
        Set<Integer> temp = events.keySet();
        for (Integer time: temp) {
            if(events.get(time) == null) {
                acc.add(time);
            }
        }
        return acc;
    }

    public ArrayList<Integer> getGiveEvents(){
        ArrayList<Integer> eventId = new ArrayList<>();
        Set<Integer> temp = events.keySet();
        for (Integer time: temp) {
            if(events.get(time) != null) {
                eventId.add(events.get(time));
            }
        }
        return eventId;
    }

    public String toString(){
        return "Speaker: " + getUsername();
    }


    @Override
    public String typeGetter(){return "Speaker";}
}
