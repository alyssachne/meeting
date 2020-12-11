package UI;

import Controller.ControllerFacade;
import Entity.Organizer;
import Gateway.ControllerRW;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws ParseException {
        ControllerRW crw = new ControllerRW();
        ControllerFacade uo = null;
        if (crw.readFile()!=null){
            uo = crw.readFile();
        }else{
//            System.out.println("Hello");
            uo = new ControllerFacade();
            crw.writeFile(uo);
//            System.out.println("Hello");
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
                uo.login(username, password);
                crw.writeFile(uo);
                if (uo.typeGetter().equalsIgnoreCase("Organizer")){
                    OrganizerUI ou = new OrganizerUI();
                    ou.OrganizerDemo(uo);
                }else if (uo.typeGetter().equalsIgnoreCase("Speaker")){
                    SpeakerUI su = new SpeakerUI();
                    su.SpeakerDemo(uo);
                }else if (uo.typeGetter().equalsIgnoreCase("User")){
                    AttendeeUI au = new AttendeeUI();
                    au.AttendeeDemo(uo);
//                        if (uo.getClass().equals(Organizer.class)){
//                            OrganizerUI ou = new OrganizerUI();
//                            ou.OrganizerDemo();
//                        }else if (uo.getClass().equals(Speaker.class)){
//                            SpeakerUI su = new SpeakerUI();
//                            su.SpeakerDemo();
//                        }else if (uo.getClass().equals(User.class)){
//                            AttendeeUI au = new AttendeeUI();
//                            au.AttendeeDemo();
                }else{
//                        System.out.println(uo.typeGetter());
                    System.out.print("User Type Error!");
                }
            }
            crw.writeFile(uo);

        }
    }
}
