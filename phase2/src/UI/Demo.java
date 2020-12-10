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
            crw.writeFile(uo);
        }
//        uo.createOrganizer();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            uo = crw.readFile();
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
                uo.createUser(name, username, password, "Attendee");
            }else if (loginChoice.equals("1")){
                System.out.println("Please enter your username:");
                String username = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                if (uo.login(username, password)) {
                    crw.writeFile(uo);
                    if (uo.typeGetter().equalsIgnoreCase("Organizer")){
                        OrganizerUI ou = new OrganizerUI();
                        ou.OrganizerDemo();
                    }else if (uo.typeGetter().equalsIgnoreCase("Speaker")){
                        SpeakerUI su = new SpeakerUI();
                        su.SpeakerDemo();
                    }else if (uo.typeGetter().equalsIgnoreCase("Attendee")){
                        AttendeeUI au = new AttendeeUI();
                        au.AttendeeDemo();
                    }else{
                        System.out.print("User Type Error!");
                    }
                }else {
                    System.out.println("The username or password does not match!");
                }
            }
            crw.writeFile(uo);

        }
    }
}
