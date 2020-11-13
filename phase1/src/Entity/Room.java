package Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room implements Serializable {
    public int MaxCapacity;
    public int id;
    //time, eventId
    public HashMap<Integer, Integer> schedule = new HashMap<>(8);
//    public HashMap<Integer, ArrayList<String>> ListOfAttendees = new HashMap<>(8);

    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
//            ListOfAttendees.put(i, null);
        }
    }

    // Get the maximum capacity of the room.
    public Integer getMaxCapacity() {
        return MaxCapacity;
    }

    // Get the current capacity of the room at given time.
//    public double getCurrentCapacity(Integer time) {
//        return ListOfAttendees.get(time).size();
//    }

    // Get the id of this room.
    public int getId() {
        return id;
    }

    // Check whether this room is full or not at the given time.
//    public boolean isFull(Integer time) {
//        return MaxCapacity == getCurrentCapacity(time);
//    }

    // Get the id of the event happens in this room at the given time.
    public int getEvent(Integer time) {
        return schedule.get(time);
    }

    // Check whether this room is booked at certain time or not.
    public boolean isBooked(Integer time) {
        return schedule.get(time) != null;
    }

    // Get a list of available time of this room.
    public ArrayList<Integer> getAvailableTime() {
        ArrayList<Integer> available = new ArrayList<>();
        for (HashMap.Entry<Integer,Integer> time: schedule.entrySet()) {
            if(time.getValue() == null) {
                available.add(time.getKey());
        }}
            return available;
    }

    // If this room is not booked at the given time, add the event to the schedule, add the list of attendee to the
    // ListOfAttendee, and return True. Else, return false.
    public boolean book(Integer eventId, Integer time) {
        if(!isBooked(time)) {
            schedule.put(time, eventId);
            return true;
        }
        return false;
    }

    public boolean cancel(Integer eventId) {
        Set<Integer> temp = schedule.keySet();
        for(Integer time: temp) {
            if(schedule.get(time).equals(eventId)) {
                schedule.replace(time, null);
                return true;
            }
        }
        return false;
    }

//    /**
//     * Add the attendee to this room and return true if the attendee is not in the ListOfAttendees or the room is not
//     * full. Else, return false. Change the state of fullness if current capacity equals maximum capacity of the room.
//     * @param username: the username of the attendee who attends to the event.
//     */
//    public boolean addAttendee(String username, Integer time) {
//        if(MaxCapacity == getCurrentCapacity(time)) {
//            return false;}
//        for (ArrayList<String> attendees: ListOfAttendees.values()) {
//            if (attendees.contains(username)) {
//                return false;
//            }
//        }
//        ArrayList<String> temp = ListOfAttendees.get(time);
//        temp.add(username);
//        return true;
//    }
//
//
//    /**
//     * Remove the attendee from this room and return true if the attendee is in the ListOfAttendees. Else, return false.
//     * @param username: the username of the attendee who is going to be removed from the ListOfAttendees.
//     */
//    public boolean removeAttendee(String username, Integer time) {
//        for (ArrayList<String> attendees: ListOfAttendees.values()) {
//            if (attendees.contains(username)) {
//                return true;
//            }
//        }
//            return false;
//        }

//    @Override
//    public String toString() {
//        StringBuilder acc = new StringBuilder("Room" + id);
//        for (int i = 9; i < 16; i++) {
//            Integer eventId = schedule.get(i);
//            if (eventId == null) {
//                String temp = " \n " + i + "00 - " + i + 1 + "00.";
//                acc.append(temp);
//            } else {
//                int current = ListOfAttendees.get(i).size();
//                String temp = " \n " + i + "00 - " + i + 1 + "00 " + eventId + " with " + current + " attendees.";
//                acc.append(temp);
//            }
//        }
//        return acc.toString();
//    }
}