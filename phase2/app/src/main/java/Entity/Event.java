package Entity;

import Usecase.EventFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The entity class for event: event object, getters, setters & toString methods
 *
 * This class mainly interacts with {@link EventFactory}: when a new event is created or existed event is modified;
 * Interact with {@link Room}: when new event is assigned to a room or the participant in an event is changed;
 * Interact with {@link Speaker}: when a Speaker is set for the event;
 * Interact with {@link Attendee}: when an Attendee sign up for the event;
 */

public abstract class Event implements Serializable {

    private final Integer id;
    private String title;
    private Date date;
    private int roomId;
    // duration is measured in hours
    private int duration;
    private List<String> speakers;
    private List<String> ListOfAttendees;
    private List<String> constraints;
    private String eventAccess;

    /**
     * Class constructor.
     * @param id The id of the event.
     * @param title The title of the event.
     * @param date The starting time of the event.
     * @param roomId The id of the room of the event.
     * @param duration The duration of the event.
     * @param speakers The username of the speaker who talks at the event.
     */
    public Event(int id, String title, Date date, int roomId, List<String> speakers, int duration, String eventAccess,
                 List<String> constraints) {
        this.title = title;
        this.date = date;
        this.roomId = roomId;
        this.duration = duration;
        this.id = id;
        this.speakers = speakers;
        ListOfAttendees = new ArrayList<>();
        this.constraints = constraints;
        this.eventAccess = eventAccess;
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

    public int getEndTime() {
        return getTime() + duration;
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
    public List<String> getSpeaker() {
        return speakers;
    }

    /**
     * Get the list of usernames of the attendees of this event.
     * @return ListOfAttendees The list of attendees of the event.
     */
    public List<String> getAttendees() {
        return ListOfAttendees;
    }

    /**
     * Get the number of attendees who attend this event.
     * @return The number of attendees who attend this event.
     */
    public int getNumOfAttendees() {
        return ListOfAttendees.size();
    }

    public String getEventAccess() {return eventAccess;}

    public List<String> getConstraints() {return constraints;}

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

    public void setEventType(String newType) {
        eventAccess = newType;
    }

    /**
     * To override the toString method to describe an event by including its details.
     * @return The string description of an event.
     */
    @Override
    public String toString() {
        List<String> s = speakers;

        return "Event ID:"+id+" This event is about " + title + ", given by " + s + ". It starts at " + date + " on Room"
                + roomId + " and it lasts for 1 hour." ;
    }
}


