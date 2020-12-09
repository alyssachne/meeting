package UI;

import Controller.ControllerFacade;
import Gateway.ControllerRW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args){
        ControllerRW crw = new ControllerRW();
        ControllerFacade uo;
        if (crw.readFile()!=null){
            uo = crw.readFile();
        }else{
            uo = new ControllerFacade();
        }
//        uo.createOrganizer();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Please enter the following options below");
            System.out.println("1.Login");
            System.out.println("2.Register Attendee Account");
            String loginChoice = scanner.nextLine();
            if (loginChoice.equals("2")){
                System.out.println("Please enter your name");
                String name = scanner.nextLine();
                System.out.println("Please enter your username:");
                String username = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                uo.createAttendee(name,username,password);
                crw.writeFile(uo);
            }else if (loginChoice.equals("1")){
                System.out.println("Please enter your username:");
                String username = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                System.out.println("Please enter your usertype:");
                String userType = scanner.nextLine();

                if (uo.login(username, password, userType) && userType.equalsIgnoreCase("Organizer")) {

                    while (handle) {

                    }
                } else if (uo.login(username, password, userType) && userType.equalsIgnoreCase("Speaker")) {
                    //call Speaker Presenter
                    boolean handle = true;
                    while (handle) {
                        System.out.println("Please enter your choice below:");
                        System.out.println("1.Show my current schedule");
                        System.out.println("2.Message System");
                        System.out.println("3.Exit");
                        String choice = scanner.nextLine();
                        if (choice.equals("1")) {
                            uo.speakerSchedule();
                        } else if (choice.equals("2")) {
                            System.out.println("1.Send group message");
                            System.out.println("2.Send private message");
                            System.out.println("3.Message Inbox");
                            String option = scanner.nextLine();
                            if (option.equals("1")){
                                System.out.println("Here are the events you give:");
                                uo.speakerSchedule();
                                System.out.println("Please enter the eventId");
                                String eventId = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.eventMessage_Attendee(message, Integer.parseInt(eventId));
                            } else if (option.equals("2")) {
                                System.out.println("Here are the events you give:");
                                uo.speakerSchedule();
                                System.out.println("Please enter the eventId");
                                String eventId = scanner.nextLine();
                                uo.checkAudiences(Integer.parseInt(eventId));
                                System.out.println("Here is a list of attendees who sign up for this event, please enter the username of the attendee you would like to send message to");
                                String contact = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.privateMessageTo(contact,"Attendee", message);
                            } else if (option.equals("3")){
                                System.out.println("Here is a list of your contacts, please enter their username to check message they sent to you");
                                uo.checkContacts();
                                String contact = scanner.nextLine();
                                uo.getMessage(contact);
                            }else {
                                System.out.println("This is not an valid option, please give a number from 1 to 3.");
                            }
                        } else if (choice.equals("3")) {
                            uo.logout();
                            crw.writeFile(uo);
                            handle = false;
                        }else {
                            System.out.println("This is not an valid option, please give a number from 1 to 3.");
                        }
                    }
                } else if (uo.login(username, password, userType) && userType.equalsIgnoreCase("Attendee")) {
                    //call Attendee Presenter
                    boolean handle = true;
                    while (handle) {
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
                            if (option.equals("1")){
                                System.out.println("Please enter the username of the attendee");
                                String other = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.privateMessageTo(other,"Attendee", message);
                            } else if (option.equals("2")) {
                                System.out.println("Here is a list of speakers you can send message to:");
                                uo.checkSpeakers();
                                System.out.println("Please enter the username of the speaker");
                                String speaker = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.privateMessageTo(speaker,"Speaker", message);
                            } else if (option.equals("3")){
                                System.out.println("Here is a list of your contacts, please enter their username to check message they sent to you");
                                uo.checkContacts();
                                String contact = scanner.nextLine();
                                uo.getMessage(contact);
                            }else {
                                System.out.println("This is not an valid option, please give a number from 1 to 3.");
                            }
                        } else if (choice.equals("5")) {
                            uo.logout();
                            crw.writeFile(uo);
                            handle = false;
                        } else if(choice.equals("6")){
                            System.out.println("1. view top-5 list");
                            System.out.println("2. view App traffic statistics");
                            System.out.println("3. view enrollment statistics");
                            String option = scanner.nextLine();
                            if (option.equals("1")){
                                uo.getTopFiveLists();
                            } else if(option.equals("2")){
                                uo.getAppTraffic();
                            } else if(option.equals("3")){
                                uo.getEnrollmentStatistics();
                            } else{
                                System.out.println("This is not an valid option, please give a number from 1 to 3.");
                            }
                        }
                        else {
                            System.out.println("This is not an valid option, please give a number from 1 to 5.");
                        }
                    }
                } else {
                    System.out.println("The username or password does not match!");
                }
            }

        }
    }
}
