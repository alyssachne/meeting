package Entity;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * The type Speaker.
 */
public class Speaker extends User {
    /**
     * The Events.
     */
// The first Integer is the started time and the second one is eventId.
    public HashMap<Integer, Integer> events = new HashMap<>(8);

    /**
     * Instantiates a new Speaker with empty slot of events from 9am to 4 pm.
     *
     * @param name     the name
     * @param username the username
     * @param password the password
     */
    public Speaker(String name, String username, String password) {
        super(name, username, password);
        for (int i = 9; i < 16; i++) {
            events.put(i, null);
        }
    }

    /**
     * Return An ArrayList of available time.
     *
     * @return the available
     */
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

    /**
     * returns an array list of events that are given.
     *
     * @return the array list
     */
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

    public HashMap<Integer, Integer> getTimetable() {
        return events;
    }
    /**
     * To string method. Returns only speaker's name
     *
     * @return the string
     */
    public String toString(){
        return "Speaker: " + getUsername();
    }


    /**
     * Type getter string.
     *
     * @return the string
     */
    @Override
    public String typeGetter(){return "Speaker";}
}
