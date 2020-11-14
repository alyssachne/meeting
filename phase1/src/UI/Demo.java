package UI;

import Controller.UserController;
import Entity.Organizer;
import Gateway.ControllerRW;
import Presenter.OrganizerPresenter;

import java.io.IOException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ControllerRW crw = new ControllerRW();
        UserController uo;
        if (crw.readFile()!=null){
            uo = crw.readFile();
        }else{
            uo = new UserController();
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
                    boolean handle = true;
                    while (handle) {
                        System.out.println("Please enter your choice below:");
                        System.out.println("1.Create Rooms");
                        System.out.println("2.Create a Speaker Account");
                        System.out.println("3.Schedule an event");
                        System.out.println("4.Message System");
                        System.out.println("5.Exit");
                        String choice = scanner.nextLine();
                        if (choice.equals("1")) {
                            System.out.println("Please enter the room id:");
                            String id = scanner.nextLine();
                            System.out.println("Please enter the max capacity of the room:");
                            String MaxCapacity = scanner.nextLine();
                            //create the room
                            uo.createRoom(Integer.parseInt(id), Integer.parseInt(MaxCapacity));
                            System.out.println("Room has been created successfully!");
                        } else if (choice.equals("2")) {
                            //speaker
                            System.out.println("Please enter name, username and password for the Speaker account");
                            System.out.println("Press Enter button after each entry");
                            String name = scanner.nextLine();
                            String uname = scanner.nextLine();
                            String pwd = scanner.nextLine();
                            uo.createSpeaker(name, uname, pwd);
                            System.out.println("Speaker account has been created successfully");
                            System.out.println("Please schedule an Event for this speaker");
                        } else if (choice.equals("3")) {
                            //Schedule an event
                            System.out.println("Here is a list of username of current speakers along with their free time slot");
                            uo.speakerList();
                            System.out.println("Here is a list of ids of the room as well as their empty time slot");
                            uo.roomList();
                            System.out.println("Please enter the username of the speaker you want to schedule for");
                            String su = scanner.nextLine();
                            System.out.println("Please enter the room id you wish the event to be hold");
                            String roomId = scanner.nextLine();
                            System.out.println("Please enter the eventId, Title, and the starting time of the event you wish to create");
                            System.out.println("Press Enter button after each entry");
                            String eventId = scanner.nextLine();
                            String title = scanner.nextLine();
                            String time = scanner.nextLine();
                            if (uo.createEvent(su, Integer.parseInt(eventId), title, Integer.parseInt(time), Integer.parseInt(roomId))) {
                                System.out.println("Event created successfully");
                            } else {
                                System.out.println("There is a time conflict exist");
                            }
                        } else if (choice.equals("4")) {
                            //message
                            System.out.println("1.Send message to all Speakers");
                            System.out.println("2.Send message to all Attendees");
                            System.out.println("3.Send message to a specific Speaker");
                            System.out.println("4.Send message to a specific Attendee");
                            System.out.println("5.Message Inbox");
                            String option = scanner.nextLine();
                            if (option.equals("1")){
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.groupMessage_Speaker(message);
                            }else if(option.equals("2")){
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.groupMessage_Attendee(message);
                            }else if (option.equals("3")){
                                System.out.println("Please enter the username of the Speaker you want to send to");
                                String receiver = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.privateMessage_Speaker(receiver,message);
                            }else if (option.equals("4")){
                                System.out.println("Please enter the username of the Attendee you want to send to");
                                String receiver = scanner.nextLine();
                                System.out.println("Please enter your message");
                                String message = scanner.nextLine();
                                uo.privateMessage_Attendee(receiver,message);
                            }else if (option.equals("5")){
                                System.out.println("Here is a list of your contacts, please enter their username to check message they sent to you");
                                uo.checkContacts_Organizer();
                                String contact = scanner.nextLine();
                                uo.getMessage_Organizer(contact);
                            }
                        } else if (choice.equals("5")) {
                            uo.logout();
                            crw.writeFile(uo);
                            handle = false;
                        }
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

                        } else if (choice.equals("3")) {
                            uo.logout();
                            crw.writeFile(uo);
                            handle = false;
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
                        System.out.println("4.Send a message");
                        System.out.println("5.Exit");
                        String choice = scanner.nextLine();
                        if (choice.equals("1")) {
                            System.out.println("Here is your current schedule");
                            uo.attendeeSchedule();
                        } else if (choice.equals("2")) {
                            System.out.println("Here is a list of events you can sign up");
                            uo.getAvailableEvent();
                            System.out.println("Please enter the eventId you would like to sign up");
                            String eventId = scanner.nextLine();
                            uo.signUp(Integer.parseInt(eventId));
                        } else if (choice.equals("3")) {
                            System.out.println("Please enter the eventId you would like to cancel");
                            String eventId = scanner.nextLine();
                            uo.cancelSpot(Integer.parseInt(eventId));
                        } else if (choice.equals("4")) {

                        } else if (choice.equals("5")) {
                            uo.logout();
                            crw.writeFile(uo);
                            handle = false;
                        }
                    }
                } else {
                    System.out.println("The username or password does not match!");
                }
            }

        }
    }
}
