package Entity;

import Usecase.EventFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * The entity class for event: event object, getters, setters & toString methods
 *
 * This class mainly interacts with {@link EventFactory}: when a new event is created or existed event is modified;
 * Interact with {@link Room}: when new event is assigned to a room or the participant in an event is changed;
 * Interact with {@link Speaker}: when a Speaker is set for the event;
 * Interact with {@link User}: when an Attendee sign up for the event;
 */

public class Event implements Serializable {

    private final Integer id;
    private String title;
    private Date date;
    private int roomId;
    // duration is measured in hours
    private int duration;
    private ArrayList<String> speakers;
    private ArrayList<String> ListOfAttendees;
    private ArrayList<String> constraints;
    private String eventAccess;

    /**
     * Class constructor.
     * @param id The id of the event.
     * @param title The title of the event.
     * @param date The starting time of the event.
     * @param roomId The id of the room of the event.
     * @param duration The duration of the event.
     */
    public Event(int id, String title, Date date, int roomId,int duration) {
        this.title = title;
        this.date = date;
        this.roomId = roomId;
        this.duration = duration;
        this.id = id;
        ListOfAttendees = new ArrayList<>();
    }

    /**
     * Get the id of the event.
     * @return id The id of the event.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the title of the event.
     * @return title The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the date the event happen.
     * @return date the date the event happen.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the date the event happen in string format.
     * @return date the date the event happen.
     */
    public String getDate_str() {
        if(9<=date.getMonth() && date.getMonth()<=11){
            return date.getDate()+"/"+(1+date.getMonth())+"/"+(1900+date.getYear());
        } else {
            return date.getDate() + "/0" + (1 + date.getMonth()) + "/" + (1900 + date.getYear());
        }
    }

    /**
     * Get the start time of the event.
     * @return time The time of the event.
     */
    public int getTime() {
        return date.getHours();
    }

    /**
     * Get the duration of the event.
     * @return duration The duration of the event.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Get the endTime of the event.
     * @return endTime The endTime of the event.
     */
    public Date getEndTime() {
        Date d = (Date) date.clone();
        d.setHours(d.getHours()+duration);
        return d;
    }

    /**
     * Get the id of the room where the event take place.
     * @return roomId The id of the room of the event.
     */
    public int getRoom() {
        return roomId;
    }

    /**
     * Get the username of the speaker who talks at the event.
     * @return speaker The username of the speaker who talks at the event.
     */
    public ArrayList<String> getSpeaker() {
        return speakers;
    }

    /**
     * Get the ArrayList of usernames of the attendees of this event.
     * @return ListOfAttendees The ArrayList of attendees of the event.
     */
    public ArrayList<String> getAttendees() {
        return ListOfAttendees;
    }

    /**
     * Get the number of attendees who attend this event.
     * @return The number of attendees who attend this event.
     */
    public int getNumOfAttendees() {
        return ListOfAttendees.size();
    }

    /**
     * Getter for the access of the event object.
     */
    public String getEventAccess() {return eventAccess;}

    /**
     * Getter for the constraints of the event object.
     * @return  constraints, An arrayList stands for the constraints that the event enforces.
     */
    public ArrayList<String> getConstraints() {return constraints;}

    /**
     * Get the title of this event.
     * @param title The title of the event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the id of the room where the event takes place.
     * @param roomId The id of the room where the event takes place.
     */
    public void setRoom(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Set the type of the room.
     * @param newType The type of the room.
     */
    public void setEventType(String newType) {
        eventAccess = newType;
    }

    /**
     * Setter for the speaker of the event object.
     * @param speakers The ArrayList representing the speaker list of the event.
     */
    public void setSpeakers(ArrayList<String> speakers){this.speakers= speakers;}

    /**
     * Setter for the constraints of the event object.
     * @param constraints The ArrayList stands for the constraints that the event enforce.
     */
    public void setConstraints(ArrayList<String> constraints){this.constraints = constraints;}

    /**
     * Setter for the access of the event object.
     * @param Access A string representing the access status that the event enforce.
     */
    public void setEventAccess(String Access){this.eventAccess=Access;}

    /**
     * To override the toString method to describe an event by including its details.
     * @return The string description of an event.
     */
    @Override
    public String toString() {
        ArrayList<String> s = speakers;
        if(s.isEmpty()){
            return "Event ID "+id+" "+ title + ". It starts at " + date  + " on Room "
                    + roomId + ", it lasts for " + duration + " hour and ends at " + getEndTime() + ". It is a party." ;
        } else if(s.size()==1){
            return "Event ID "+id + " "+title + ", given by " + s.get(0) + ". It starts at " + date  + " on Room "
                    + roomId + ", it lasts for " + duration + " hour and ends at " + getEndTime() + ". It is a talk." ;
        }else{
            return "Event ID "+id+" "+ title + ", given by " + s + ". It starts at " + date  + " on Room "
                    + roomId + ", it lasts for " + duration + " hour and ends at " + getEndTime() + ". It is a discussion." ;
        }
    }
}


