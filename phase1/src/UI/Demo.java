package UI;

import Controller.UserController;
import Entity.Organizer;
import Presenter.OrganizerPresenter;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        UserController uo = new UserController();
        uo.createOrganizer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
//        System.out.println("Username is: " + username);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        System.out.println(uo.Login(username, password));

        if (uo.Login(username, password) && uo.userType().equals("Organizer")) {
            OrganizerPresenter op = new OrganizerPresenter();
            op.mainMenu();
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                op.roomMenu();
                String id = scanner.nextLine();
                String MaxCapacity = scanner.nextLine();
                //create the room
            } else if (choice.equals("2")) {
                //speaker
            } else if (choice.equals("3")) {
                //message
            }
        } else if (uo.Login(username, password) && uo.userType().equals("Speaker")) {
            //call Speaker Presenter
        } else if (uo.Login(username, password) && uo.userType().equals("Attendee")) {
            //call Attendee Presenter
        } else {
            System.out.println("The username or password does not match!");
        }
    }
}
