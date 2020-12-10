package UI;

import Controller.ControllerFacade;
import Gateway.ControllerRW;

import java.util.Scanner;

public class SpeakerUI {

    public void SpeakerDemo() {
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
                        uo.privateMessageTo(contact, "Attendee", message);
                    } else {
                        System.out.println("This is not an valid option, please give a number from 1 to 2.");
                    }

                } else if (decision.equals("2")) {
                    System.out.println("1.New message");
                    System.out.println("2.Read message");
                    System.out.println("3.Archived message");
                    System.out.println("Please choose which box you want to access");
                    String box = scanner.nextLine();
                    System.out.println("You want to see all message or message from one person? If you want to see all" +
                            "please input 'all'. ");
                    String sender = scanner.nextLine();
                    // can read message in each box
                    if (sender.equals("all")) {
                        System.out.println("Here are messages in" + box + " :");
                        uo.readAllMessage(box);
                    } else {
                        System.out.println("Here is a list of your contacts, please enter their username to check " +
                                "unread message from them");
                        uo.checkContacts(box);
                        String contact = scanner.nextLine();
                        uo.getMessage(contact, box);
                    }

                    // need an else here to handle exception for number from 1 to 3


                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 2.");
                }
            } else if (choice.equals("3")) {
                uo.logout();
                crw.writeFile(uo);
                handle = false;
            } else {
                System.out.println("This is not an valid option, please give a number from 1 to 3.");
            }
        }
    }

    }
