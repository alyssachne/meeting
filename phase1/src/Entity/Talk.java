package Entity;

import java.util.ArrayList;

public class Talk {

    public int id;
    public double duration;
    public String title;
    public double time;
    public int roomId;
    public String speaker;
    public ArrayList<String> ListOfAttendees;

    public Talk(int id, double duration) {
        this.id = id;
        this.duration = duration;
        ArrayList<Attendee> ListOfAttendees = new ArrayList<Attendee>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getTime() {
        return time;
    }

    public int getRoom() {
        return roomId;
    }

    public String getSpeaker() {
        return speaker;
    }

    public ArrayList<String> getAttendees() {
        return ListOfAttendees;
    }

    public int getNumOfAttendees() {
        return ListOfAttendees.size();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setRoom(int roomId) {
        this.roomId = roomId;
    }

    public void setSpeaker(String username) {
        speaker = username;
    }

    // Here need a method in Attendee to compare to attendee to see if they are identical.
    public boolean addAttendee(Attendee attendee) {
        String newName = attendee.getUsername();
        for (String name : ListOfAttendees) {
            if(newName.equals(name)) {
                return false;
            }
        }
        ListOfAttendees.add(newName);
        return true;
    }
//same here.
    public boolean removeAttendee(Attendee attendee) {
        String newName = attendee.getUsername();
        for (String name: ListOfAttendees) {
            if(newName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}


