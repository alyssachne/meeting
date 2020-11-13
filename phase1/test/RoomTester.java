import Entity.Room;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomTester {
    @Test
    public void testRoom() {
        Room room1 = new Room(1, 3);
        Room room2 = new Room(2, 3);
        Room room3 = new Room(3, 3);
    }

    @Test
    public void testGetMaxCapacity() {
        Room room1 = new Room(1, 3);
        Room room2 = new Room(2, 10);
        Room room3 = new Room(3, 2020);
        assertEquals(3, room1.getMaxCapacity(), 0);
        assertEquals(10, room2.getMaxCapacity(), 0);
        assertEquals(2020, room3.getMaxCapacity(), 0);
    }

    @Test
    public void testGetCurrentCapacity() {
        Room room1 = new Room(1, 3);
        assertEquals(0, room1.getCurrentCapacity(9),0);
        room1.addAttendee("username1", 9);
        assertEquals(1, room1.getCurrentCapacity(9),0);
        room1.addAttendee("username2", 9);
        assertEquals(2, room1.getCurrentCapacity(9),0);
        room1.addAttendee("username1", 10);
        room1.addAttendee("username1", 13);
        room1.addAttendee("username2", 13);
        room1.addAttendee("username3", 13);
        assertEquals(1, room1.getCurrentCapacity(10),0);
        assertEquals(3, room1.getCurrentCapacity(13),0);
    }

    @Test
    public void testGetId() {
        Room room1 = new Room(1, 3);
        Room room2 = new Room(2, 3);
        Room room3 = new Room(3, 3);
        assertEquals(1, room1.getId(), 0);
        assertEquals(2, room2.getId(), 0);
        assertEquals(3, room3.getId(), 0);
    }

    @Test
    public void testIsFull() {
        Room room1 = new Room(1, 3);
        room1.addAttendee("username1", 9);
        room1.addAttendee("username1", 9);
        assertFalse(room1.isFull(9));
        room1.addAttendee("username3", 9);
        assertTrue(room1.isFull(9));
    }

    @Test
    public void testGetEvent() {
        Room room1 = new Room(1, 3);
        ArrayList<String> ListOfAttendeeOfEvent1 = new ArrayList<>();
        ListOfAttendeeOfEvent1.add("username1");
        ListOfAttendeeOfEvent1.add("username2");
        ArrayList<String> ListOfAttendeeOfEvent2 = new ArrayList<>();
        ListOfAttendeeOfEvent2.add("username1");
        ListOfAttendeeOfEvent2.add("username2");
        ListOfAttendeeOfEvent2.add("username3");
        room1.Book(1, 9, ListOfAttendeeOfEvent1);
        room1.Book(2, 15, ListOfAttendeeOfEvent2);
        assertEquals(1, room1.getEvent(9));
        assertEquals(2, room1.getEvent(15));
//      assertEquals(0, room1.getEvent(12));
    }

    @Test
    public void testIsBooked() {
        Room room1 = new Room(1, 3);
        ArrayList<String> ListOfAttendeeOfEvent1 = new ArrayList<>();
        ListOfAttendeeOfEvent1.add("username1");
        ListOfAttendeeOfEvent1.add("username2");
        room1.Book(1, 9, ListOfAttendeeOfEvent1);
        assertTrue(room1.isBooked(9));
        assertFalse(room1.isBooked(12));
    }
}
