package Entity;
import java.util.*;

/**
 * The type Speaker.
 */
public class Speaker extends User {
    /**
     * The Events.
     */
// The first Integer is the date and the second one is a list of eventId.
    public HashMap<Date, ArrayList<Integer>> events;

    /**
     * Instantiates a new Speaker with empty slot of events from 9am to 4 pm.
     *
     * @param name     the name
     * @param username the username
     * @param password the password
     */
    public Speaker(String name, String username, String password) {
        super(name, username, password);
        events = new HashMap<>();
    }

    /**
     * returns an array list of events that are given on that day.
     *
     * @return the array list
     */
    public ArrayList<Integer> getGiveEvents_OneDay(Date date){
        if(!events.containsKey(date)){
            events.put(date, new ArrayList<>(8));

        }
        return events.get(date);
    }

    /**
     * returns a hash map of dates and all events that are given.
     *
     * @return the array list
     */
    public HashMap<Date, ArrayList<Integer>> getAllGivenEvents(){
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
