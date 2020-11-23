package Entity;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The entity class for event: event object, getters, setters & toString methods
 *
 * This class mainly interacts with {@link Usecase.EventManager}: when a new event is created or existed event is modified;
 * Interact with {@link Room}: when new event is assigned to a room or the participant in an event is changed;
 * Interact with {@link Speaker}: when a Speaker is set for the event;
 * Interact with {@link Attendee}: when an Attendee sign up for the event;
 */

public class Event implements Serializable {

    private Integer id;
    private String title;
    private Integer time;
    private int roomId;
    // The first string is the username of the speaker and the second string is the name of the speaker.
    private String speaker;
    private ArrayList<String> ListOfAttendees;

    /**
     * Class constructor.
     * @param id The id of the event.
     * @param title The title of the event.
     * @param time The starting time of the event.
     * @param roomId The id of the room of the event.
     * @param speaker The username of the speaker who talks at the event.
     */
    public Event(int id, String title, int time, int roomId, String speaker) {
        this.title = title;
        this.time = time;
        this.roomId = roomId;
        this.id = id;
        this.speaker = speaker;
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
     * Get the start time of the event.
     * @return time The time of the event.
     */
    public Integer getTime() {
        return time;
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
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Get the list of usernames of the attendees of this event.
     * @return ListOfAttendees The list of attendees of the event.
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
     * Get the title of this event.
     * @param title The title of the event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the start time of this event.
     * @param time The starting time of the event.
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * Set the id of the room where the event takes place.
     * @param roomId The id of the room where the event takes place.
     */
    public void setRoom(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Set the username of the speaker who talks at this event.
     * @param username The username of the speaker who talks at the event.
     */
    public void setSpeaker(String username) {
        this.speaker = username;
    }

    /**
     * To override the toString method to describe an event by including its details.
     * @return The string description of an event.
     */
    @Override
    public String toString() {
        String s = speaker;

        return "Event ID:"+id+" This event is about " + title + ", given by " + s + ". It starts at " + time + " on Room"
                + roomId + " and it lasts for 1 hour." ;
    }
}


