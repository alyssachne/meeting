import Entity.Attendee;
import Entity.Event;
import Entity.Speaker;
import org.junit.Test;
import static org.junit.Assert.*;

class TestJunit {
    @Test
    public static void main(String[] args) {
        System.out.println("Test if Junit is successfully imported.");
    }
}

public class EventTester {

    @Test
    // Test the constructor of Event
    public static void main(String[] args) {
        Event newEvent = new Event(1,"Polymorphism",322,6);
        assert(newEvent.id==1);
        assert (newEvent.title == "Polymorphism");
        assert (newEvent.time==322);
        assert (newEvent.roomId == 6);
    }

    @Test
    //Test if Two Events can share the same id
    public void TestIdConflict() {
        Event Event1 = new Event(1);
        Event Event2 = new Event(1);
        assertFalse (Event1.id== Event2.id);
    }

    @Test
    //Test the getter of id
    public void TestgetId(){
        Event Event1 = new Event(1);
        assertTrue(Event1.id==1);
    }

    @Test
    //Test setTitle()
    public void testSetTitle(){
        Event Event1 = new Event(1);
        Event1.setTitle("Polymorphism");
        assertEquals(Event1.getTitle(), "Polymorphism");
    }

    @Test
    //Test getTitle
    public void testGetTitle(){
        Event Event1 = new Event(1);
        Event1.setTitle("Polymorphism");
        assertEquals("Polymorphism", Event1.getTitle());
    }

    @Test
    //Test setRoom() and getRoom() , change the access modifier to private
    public void testSGetRoom(){
        Event Event1 = new Event(1);
        Event1.setRoom(1);
        assertEquals(1, Event1.getRoom());
    }

    @Test
    // Test setTime and getTime()
    public void testSGTime(){
        Event Event1 = new Event(1);
        Event1.setTime(996);
        assertEquals(996, (int) Event1.getTime());
    }

    @Test
    // Test that time can not be set to an irregular time, suggest that make setter return boolean when wrong fomat of time is input
    public void testMeaningfulTime(){
        Event Event1 = new Event(1);
        assertFalse(Event1.setTime(996));
    }

    @Test
    // Test setting and getting speakers, suggest that make this setter return boolean, when such speaker does not
    // exist, return false, return true only when such speaker exist and the name and username match.
    public void testSGspeaker(){
        Event Event1 = new Event(1);
        assertFalse(Event1.setSpeaker("Kevin","Kevin"));
        Speaker Kevin = new Speaker("Kevin", "Kevin8","passward");
        Event1.setSpeaker("Kevin8","Kevin");
        assertEquals("Kevin8", Event1.getSpeaker().getKey());
        assertEquals("Kevin", Event1.getSpeaker().getValue());
    }

    @Test
    // Test add attendees and get attendees as well as remove attendees, return false for non-exiting attendees and
    // and attendees that is not free during this time.
    // This class need to collaborate with schedule class and attendee class, also tested below.
    public void testAttendees(){
        Event Event1 = new Event(1);
        Event1.setTime(100);
        Event Event2 = new Event(2);
        Event2.setTime(100);
        assertFalse(Event1.addAttendee("Kevin8")); //This attendee does not eixst yet
        Attendee Kevin = new Attendee("Kevin", "Kevin8","passward");
        //The name and user name must match
        assertFalse(Event1.addAttendee("Kevin8","Kevin"));
        assertTrue(Event1.addAttendee("Kevin8","Kevin"));
        // Test that an Attendee can not sign up for two events at the same time
        Kevin.signUp(2);
        assertFalse(Event1.addAttendee("Kevin8","Kevin"));
        assertTrue(Event2.getAttendees().contains("Kevin8"));
        assertEquals(Event2.getNumOfAttendees(),1);
        Event2.removeAttendee("Kevin8");
        assertEquals(Event2.getNumOfAttendees(),0);
    }
}
