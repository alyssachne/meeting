import Entity.Room;
import Usecase.RoomManager;
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
        assertEquals(3, room1.getMaxCapacity(),0);
        assertEquals(10, room2.getMaxCapacity(), 0);
        assertEquals(2020, room3.getMaxCapacity(), 0);
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
    public void testGetEvent() {
        RoomManager roomManager1 = new RoomManager();
        roomManager1.createRoom(1, 3);
        roomManager1.book(1,1,9);
        assertEquals(1, roomManager1.getRoom(1).getEvent(9));
        assertEquals(0, roomManager1.getRoom(1).getEvent(10));
    }

    @Test
    public void testIsBooked() {
        RoomManager roomManager1 = new RoomManager();
        roomManager1.createRoom(1, 3);
        roomManager1.book(1,1,9);
        assertTrue(roomManager1.getRoom(1).isBooked(9));
        assertFalse(roomManager1.getRoom(1).isBooked(10));
    }
}
