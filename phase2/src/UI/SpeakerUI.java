package UI;

import Controller.ControllerFacade;
import Gateway.ControllerRW;

import java.util.Scanner;

public class SpeakerUI {

    public void SpeakerDemo(ControllerFacade uo) {
//        ControllerRW crw = new ControllerRW();
//        ControllerFacade uo= null;
//        if (crw.readFile() != null) {
//            uo = crw.readFile();
//        }else {
//            System.out.println("Read File Error");
//        }

        Scanner scanner = new Scanner(System.in);
        boolean handle = true;
        while (handle) {
//            uo = crw.readFile();
            System.out.println("Please enter your choice below:");
            System.out.println("1.Show my current schedule");
            System.out.println("2.Message System");
            System.out.println("3.Exit");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                uo.speakerSchedule();
                // there should have options for them to filter or sort their schedule.
            } else if (choice.equals("2")) {
                System.out.println("1.Send message");
                System.out.println("2.Message Inbox");
                String decision = scanner.nextLine();
                if (decision.equals("1")) {
                    System.out.println("1.Send group message");
                    System.out.println("2.Send private message");
                    String option = scanner.nextLine();
                    System.out.println("Here are the events you give:");
                    uo.speakerSchedule();
                    System.out.println("Please enter the eventId");
                    String eventId = scanner.nextLine();
                    System.out.println("Please enter your message");
                    String message = scanner.nextLine();
                    if (option.equals("1")) {
                        uo.eventMessage_Attendee(message, Integer.parseInt(eventId));
                    } else if (option.equals("2")) {
                        uo.checkAudiences(Integer.parseInt(eventId));
                        System.out.println("Here is a list of attendees who sign up for this event, please enter the username of the attendee you would like to send message to");
                        String contact = scanner.nextLine();
                        uo.privateMessageTo(contact, message);
                    } else {
                        System.out.println("This is not an valid option, please give a number from 1 to 2.");
                    }

                } else if (decision.equals("2")) {
                    MessageUI mu = new MessageUI();
                    mu.MessageDemo(uo);
                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 3.");
                }
            } else if (choice.equals("3")) {
                uo.logout();
//                crw.writeFile(uo);
                handle = false;
            } else {
                System.out.println("This is not an valid option, please give a number from 1 to 3.");
            }
//            crw.writeFile(uo);
        }
    }
}
