import Usecase.EventManager;
import Entity.Event;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class EventManagerTest {

    // Event Manager constructor
    @Test(timeout = 50)
    public void testEventManager(){
        EventManager em = new EventManager();
    }

    // createEvent same time different rooms
    @Test
    public void testCreateEvent1(){
        EventManager em1 = new EventManager();
        em1.createEvent(1, "aaa",9,1, "a");
        em1.createEvent(2,"bbb",9,2, "b");
        em1.createEvent(3,"ccc",9,3, "c");

        assertEquals(new Event(1,"aaa",9,1, "a"),em1.getEvent(1));
        assertEquals(new Event(2,"bbb",9,2, "b"),em1.getEvent(2));
        assertEquals(new Event(3,"ccc",9,3, "c"),em1.getEvent(3));
    }

    // createEvent same room different times
    @Test
    public void testCreateEvent2(){
        EventManager em2 = new EventManager();
        em2.createEvent(1, "aaa",9,1, "a");
        em2.createEvent(2,"bbb",10,1, "b");
        em2.createEvent(3,"ccc",11,1, "c");

        assertEquals(new Event(1,"aaa",9,1, "a"),em2.getEvent(1));
        assertEquals(new Event(2,"bbb",10,1, "b"),em2.getEvent(2));
        assertEquals(new Event(3,"ccc",11,1, "c"),em2.getEvent(3));
    }

    // createEvent event time out of range
    @Test
    public void testCreateEvent3(){
        EventManager em3 = new EventManager();
        em3.createEvent(1, "aaa",8,1, "a");
        em3.createEvent(2,"bbb",10,2, "b");
        em3.createEvent(3,"ccc",17,3, "c");

        assertNull(em3.getEvent(1));
        assertEquals(new Event(2,"bbb",10,2, "b"),em3.getEvent(2));
        assertNull(em3.getEvent(3));
    }

    // createEvent event room and time conflict
    @Test
    public void testCreateEvent4(){
        EventManager em4 = new EventManager();
        em4.createEvent(1, "aaa",9,2, "a");
        em4.createEvent(2,"bbb",10,2, "b");
        em4.createEvent(3,"ccc",10,2, "c");

        assertEquals(new Event(1,"aaa",9,2, "a"),em4.getEvent(1));
        assertEquals(new Event(2,"bbb",10,2, "b"),em4.getEvent(2));
        assertNull(em4.getEvent(3));
    }

    // createEvent same speaker different times
    @Test
    public void testCreateEvent5(){
        EventManager em5 = new EventManager();
        em5.createEvent(1, "aaa",9,1, "a");
        em5.createEvent(2,"bbb",10,1, "a");
        em5.createEvent(3,"ccc",11,1, "a");

        assertEquals(new Event(1,"aaa",9,1, "a"),em5.getEvent(1));
        assertEquals(new Event(2,"bbb",10,1, "a"),em5.getEvent(2));
        assertEquals(new Event(3,"ccc",11,1, "a"),em5.getEvent(3));
    }

    // createEvent speaker and time conflict
    @Test
    public void testCreateEvent6(){
        EventManager em6 = new EventManager();
        em6.createEvent(1, "aaa",9,1, "a");
        em6.createEvent(2,"bbb",10,2, "a");
        em6.createEvent(3,"ccc",10,3, "a");

        assertEquals(new Event(1,"aaa",9,1, "a"),em6.getEvent(1));
        assertEquals(new Event(2,"bbb",10,2, "b"),em6.getEvent(2));
        assertNull(em6.getEvent(3));
    }

    // getEvent can be tested together with create Event
    // not sure what setTime means

    // addAttendee Test
    @Test
    public void testAddAttendee(){
        EventManager em01 = new EventManager();
        em01.addAttendee("a1",1);

        assertTrue(em01.getEvent(1).getAttendees() == new ArrayList<String>());
    }

}
