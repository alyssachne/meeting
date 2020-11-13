import Entity.Attendee;
import Entity.Event;
import Entity.Room;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttendeeTester {
    @Test
    /*Test if signUp() can
    (1) add event to the attendee's event list
    (2) Update corresponding event's attendee list
    (3) Update the list of people in the room that will hold the event
    (4) Return false if the event/room is fully booked
     */
    public void testSignUp(){
        Event CSC207Tutorial = new Event(1);
        Room BA1130 = new Room(1,3);
        CSC207Tutorial.setRoom(1);
        Attendee Kevin = new Attendee("Kevin","Kevin8","verySafePassward");
        Attendee Tom = new Attendee("Tom","Tom8","verySafePassward");
        Attendee WangYiBo = new Attendee("WangYiBo","WangYiBo8","verySafePassward");
        Kevin.signUp(1);
        assertTrue(Kevin.getEvents().contains(1));
        assertEquals(CSC207Tutorial.getNumOfAttendees(),1);
        assertEquals(BA1130.getCurrentCapacity(CSC207Tutorial.time),2,0);
        Tom.signUp(1);
        assertFalse(WangYiBo.signUp(1));
    }
}
