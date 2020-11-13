package UI;

import Controller.UserController;
import Entity.Organizer;
import Presenter.OrganizerPresenter;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        UserController uo = new UserController();
//        uo.createOrganizer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
//        System.out.println("Username is: " + username);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        System.out.println("Please enter your usertype:");
        String userType = scanner.nextLine();

        if (uo.login(username, password,userType) && userType.equalsIgnoreCase("Organizer")) {
            boolean handle = true;
            while (handle){
                System.out.println("Please enter your choice below:");
                System.out.println("1.Create Rooms");
                System.out.println("2.Create a Speaker Account");
                System.out.println("3.Schedule an event");
                System.out.println("4.Send a message");
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
                    uo.createSpeaker(name,uname,pwd);
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
                    if(uo.createEvent(su,Integer.parseInt(eventId),title,Integer.parseInt(time),Integer.parseInt(roomId))){
                        System.out.println("Event created successfully");
                    }else{
                        System.out.println("There is a time conflict exist");
                    }
                }else if(choice.equals("4")){
                    //message
                }else if (choice.equals("5")){
                    handle = false;
                }
            }
        } else if (uo.login(username, password,userType) && userType.equalsIgnoreCase("Speaker")) {
            //call Speaker Presenter
        } else if (uo.login(username, password,userType) && userType.equalsIgnoreCase("Attendee")) {
            //call Attendee Presenter
        } else {
            System.out.println("The username or password does not match!");
        }
    }
}
