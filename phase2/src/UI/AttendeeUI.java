package UI;

import Controller.ControllerFacade;
import Gateway.ControllerRW;

import java.util.Scanner;

public class AttendeeUI {

    public void AttendeeDemo() {
        ControllerRW crw = new ControllerRW();
        ControllerFacade uo;
        if (crw.readFile() != null) {
            uo = crw.readFile();
        } else {
            uo = new ControllerFacade();
        }

        Scanner scanner = new Scanner(System.in);

        boolean handle = true;
        while (handle) {
            if (uo.checkAccess().equalsIgnoreCase("VIP")){
                System.out.print("Welcome VIP!");
            }
            System.out.println("Please enter your choice below:");
            System.out.println("1.Show my current schedule");
            System.out.println("2.Sign up an event");
            System.out.println("3.Cancel an event reservation");
            System.out.println("4.Message system");
            System.out.println("5.Exit");
            System.out.println("6. View enrollment Statistics");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Here is your current schedule");
                uo.attendeeSchedule();
                // they can sort and filter, need to be added
            } else if (choice.equals("2")) {
                System.out.println("Here is a list of events you can sign up");
                uo.getAvailableEvent("Speaker");
                System.out.println("Please enter the eventId you would like to sign up");
                String eventId = scanner.nextLine();
                uo.signUp(Integer.parseInt(eventId));
            } else if (choice.equals("3")) {
                System.out.println("Please enter the eventId you would like to cancel");
                String eventId = scanner.nextLine();
                uo.cancelSpot(Integer.parseInt(eventId));
            } else if (choice.equals("4")) {
                System.out.println("1.Send message to attendee");
                System.out.println("2.Send message to speaker");
                System.out.println("3.Message Inbox");
                String option = scanner.nextLine();
                if (option.equals("1")) {
                    System.out.println("Please enter the username of the attendee");
                    String other = scanner.nextLine();
                    System.out.println("Please enter your message");
                    String message = scanner.nextLine();
                    uo.privateMessageTo(other, "Attendee", message);
                } else if (option.equals("2")) {
                    System.out.println("Here is a list of speakers you can send message to:");
                    uo.checkSpeakers();
                    System.out.println("Please enter the username of the speaker");
                    String speaker = scanner.nextLine();
                    System.out.println("Please enter your message");
                    String message = scanner.nextLine();
                    uo.privateMessageTo(speaker, "Speaker", message);
                } else if (option.equals("3")) {
                    System.out.println("Here is a list of your contacts, please enter their username to check message they sent to you");
                    uo.checkContacts();
                    String contact = scanner.nextLine();
                    uo.getMessage(contact);
                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 3.");
                }
            }
        }
    }
}
