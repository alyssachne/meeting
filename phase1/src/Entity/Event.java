package Entity;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    public Integer id;
    public String title;
    public Integer time;
    public int roomId;
    // The first string is the username of the speaker and the second string is the name of the speaker.
    public String speaker;
    public ArrayList<String> ListOfAttendees;

    public Event(int id, String title, int time, int roomId, String speaker) {
        this.title = title;
        this.time = time;
        this.roomId = roomId;
        this.id = id;
        this.speaker = speaker;
        ArrayList<Attendee> ListOfAttendees = new ArrayList<Attendee>();
    }

    // Get the id of the event.
    public int getId() {
        return id;
    }

    // Get the title of the event.
    public String getTitle() {
        return title;
    }

    // Get the start time of the event.
    public Integer getTime() {
        return time;
    }

    // Get the id of the room where the event take place.
    public int getRoom() {
        return roomId;
    }

    // Get the name and username of the speaker who give the talk.
    public String getSpeaker() {
        return speaker;
    }

    // Get the list of usernames of the attendees of this talk.
    public ArrayList<String> getAttendees() {
        return ListOfAttendees;
    }

    // Get the number of attendees who attend this talk.
    public int getNumOfAttendees() {
        return ListOfAttendees.size();
    }

    // Get the title of this event.
    public void setTitle(String title) {
        this.title = title;
    }

    // Set the start time of this event.
    public void setTime(Integer time) {
        this.time = time;
    }
    
    // Set the id of the room where the event takes place.
    public void setRoom(int roomId) {
        this.roomId = roomId;
    }

    // Set the username of the speaker who gives this event.
    public void setSpeaker(String username) {
        this.speaker = username;
    }

    // Add the username of the given attendee to the Attendees list if the attendee is not on the list.
    public boolean addAttendee(String attendee) {
        for (String name : ListOfAttendees) {
            if(attendee.equals(name)) {
                return false;
            }
        }
        ListOfAttendees.add(attendee);
        return true;
    }

    // Remove the username of the given attendee from the Attendees list if the attendee is on the list.
    public boolean removeAttendee(String attendee) {
        for (String name: ListOfAttendees) {
            if(attendee.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = speaker;

        return "This event is about " + title + ", given by " + s + ". It starts at " + time + " on Room"
                + roomId + " and it lasts for 1 hour." ;
    }
}


