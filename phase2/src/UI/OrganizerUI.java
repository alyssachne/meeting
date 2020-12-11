package UI;

import Controller.ControllerFacade;
import Entity.Organizer;
import Gateway.ControllerRW;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrganizerUI {

    public void OrganizerDemo(ControllerFacade uo){
//        ControllerRW crw = new ControllerRW();
//        ControllerFacade uo = uo;
//        if (crw.readFile()!=null){
//            uo = crw.readFile();
//        }else {
//            System.out.println("Read File Error");
//        }

        Scanner scanner = new Scanner(System.in);
        boolean handle = true;
        while(handle){
//            uo = crw.readFile();
            System.out.println("Please enter your choice below:");
            System.out.println("1.Create Rooms");
            System.out.println("2.Create an Account");
            System.out.println("3.Schedule an event");
            System.out.println("4.Message System");
            System.out.println("5.Request System");
            System.out.println("6.Statistics Summary");
            System.out.println("7.Exit");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Please enter the max capacity of the room:");
                String MaxCapacity = scanner.nextLine();
                boolean constraintLoop = true;
                ArrayList<String> constraints = new ArrayList<>();
                while (constraintLoop) {
                    System.out.println("Please enter the constraints for this room(Enter exit to skip finish):");
                    String constraint = scanner.nextLine();
                    if (constraint.equals("exit")) {
                        constraintLoop = false;
                    } else {
                        constraints.add(constraint);
                    }
                }
                //create the room
                uo.createRoom(Integer.parseInt(MaxCapacity), constraints);
                System.out.println("Room has been created successfully!");
            } else if (choice.equals("2")) {
                //speaker
                System.out.println("1.Create an Organizer Account");
                System.out.println("2.Create an Speaker Account");
                System.out.println("3.Create an Normal Attendee Account");
                System.out.println("4.Create an VIP Attendee Account");
                String option = scanner.nextLine();
                if (option.equals("1")||option.equals("2")||option.equals("3")||option.equals("4")) {
                    System.out.println("Please enter name, username and password for the account");
                    System.out.println("Press Enter button after each entry");
                    String name = scanner.nextLine();
                    String uname = scanner.nextLine();
                    String pwd = scanner.nextLine();
                    if (option.equals("1")){
                        uo.createUser(name, uname, pwd, "Organizer");
                        System.out.println("Organizer account has been created successfully");
                    }else if (option.equals("2")){
                        uo.createUser(name, uname, pwd, "Speaker");
                        System.out.println("Speaker account has been created successfully");
                        System.out.println("Please schedule an Event for this speaker");
                    }else if (option.equals("3")){
                        uo.createUser(name, uname, pwd, "Attendee");
                        System.out.println("Attendee account has been created successfully");
                    }else if (option.equals("4")){
                        uo.createUser(name, uname, pwd, "Attendee");
                        uo.changeUserAccess(uname, "VIP");
                        System.out.println("VIP Attendee account has been created successfully");
                    }
                }else{
                    System.out.print("This is not an valid option, please give a number from 1 to 4.");
                }
            } else if (choice.equals("3")&&uo.hasRoom()) {
                //Schedule an event
                System.out.println("1.Create a panel discussion");
                System.out.println("2.Create a party");
                System.out.println("3.Create a talk");
                String option = scanner.nextLine();
                if ((option.equals("1")&&uo.hasSpeaker())||option.equals("2")||(option.equals("3")&&uo.hasSpeaker())){
                    System.out.print("Please enter date of the event will be held(DD/MM/YYYY):");
                    Date date = null;
                    try{
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                    }catch (Exception e){
                        System.out.print("Not a valid input!");
                    }
                    if (choice.equals("1")||choice.equals("3")){
                        System.out.println("Here is a list of username of current speakers along with their free time slot:");
                        try{
                            uo.speakerAvailable(date);
                        }catch (Exception e){
                            System.out.print("Don't have speaker available, please choose a new date or create a new" +
                                    "speaker.");
                        }
                    }
                    boolean constraintLoop = true;
                    ArrayList<String> constraints = new ArrayList<>();
                    while (constraintLoop) {
                        System.out.println("Please enter the constraints for the event(Enter exit to skip finish):");
                        String constraint = scanner.nextLine();
                        if (constraint.equals("exit")) {
                            constraintLoop = false;
                        } else {
                            constraints.add(constraint);
                        }
                    }
                    System.out.println("Please enter the maximum capacity you want for the room");
                    String max = scanner.nextLine();
                    System.out.println("Here is a list of ids of the room as well as their empty time slot:");
                    try{
                        uo.roomList(date,Integer.parseInt(max),constraints);
                    }catch (Exception e){
                        System.out.print("Don't have room available, please choose a new date or create a new room.");
                    }
//                    uo.roomList(date,Integer.parseInt(max), constraints);
                    ArrayList<String> speakerList = new ArrayList<>();
                    if (choice.equals("1")){
                        boolean speakerLoop = true;
                        while (speakerLoop) {
                            System.out.println("Please enter the username of the speaker you want to schedule for(Enter exit to skip finish):");
                            String su = scanner.nextLine();
                            if (su.equals("exit")) {
                                speakerLoop = false;
                            } else {
                                speakerList.add(su);
                            }
                        }
                    } else{
                        System.out.println("Please enter the username of the speaker you want to schedule for(Enter exit to skip finish):");
                        String su = scanner.nextLine();
                        speakerList.add(su);
                    }
                    System.out.println("Please enter the room id you wish the event to be hold");
                    String roomId = scanner.nextLine();
                    System.out.println("Please enter the Duration(in hours), Title, and the starting time of the event you wish to create");
                    System.out.println("Press Enter button after each entry");
                    String duration = scanner.nextLine();
                    String title = scanner.nextLine();
                    String time = scanner.nextLine();
                    System.out.print("Would you want to make it a VIP event(Y/N)?");
                    String YN = scanner.nextLine();
                    if (YN.equalsIgnoreCase("Y")){
                        YN = "VIP";
                    }else if (YN.equalsIgnoreCase("N")){
                        YN = "Normal";
                    }else{
                        System.out.print("Not a valid input");
                    }
                    if (uo.createEvent(speakerList, title, date, Integer.parseInt(time), Integer.parseInt(roomId),Integer.parseInt(duration),YN, constraints)) {
                        System.out.println("Event created successfully");
                    } else {
                        System.out.println("There is a time conflict exist");
                    }
                }else{
                    if (!uo.hasSpeaker()){
                        System.out.print("There is no speaker available.");
                    }else{
                        System.out.print("This is not an valid option, please give a number from 1 to 3.");
                    }
                }
            } else if (choice.equals("4")) {
                //message
                System.out.println("1.Send message");
                System.out.println("2.Message Inbox");
                String decision = scanner.nextLine();
                if (decision.equals("1")) {
                    System.out.println("1.Send message to all Speakers");
                    System.out.println("2.Send message to all Attendees");
                    System.out.println("3.Send message to a specific User");
                    String option = scanner.nextLine();
                    System.out.println("Please enter your message");
                    String message = scanner.nextLine();
                    if (option.equals("1")) {
                        uo.groupMessageTo(message, "Speaker");
                    } else if (option.equals("2")) {
                        uo.groupMessageTo(message, "Attendee");
                    } else if (option.equals("3")) {
                        System.out.println("Please enter the username of the User you want to send to");
                        String receiver = scanner.nextLine();
                        uo.privateMessageTo(receiver, message);
                    } else {
                        System.out.println("This is not an valid option. Please choose a number from 1 to 3");
                    }
                } else if (decision.equals("2")) {
                    MessageUI mu = new MessageUI();
                    mu.MessageDemo(uo);
                }else{
                    System.out.println("This is not an valid option, please give a number from 1 to 2.");
                }
            } else if(choice.equals("5")) {
                System.out.println("Here are all requests: ");
                uo.checkAllRequest();
                System.out.println("Please enter the id of the request you want to address");
                String id = scanner.nextLine();
                uo.addressRequest(Integer.parseInt(id));
            } else if(choice.equals("6")){
                System.out.println("1.Enrollment Statistics");
                System.out.println("2.Top Five Event Lists");
                System.out.println("3.App Traffic");
                String stat = scanner.nextLine();
                if(stat.equals("1")){
                    uo.getEnrollmentStatistics();
                } else if(stat.equals("2")){
                    uo.getTopFiveLists();
                } else if(stat.equals("3")) {
                    uo.getAppTraffic();
                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 3.");
                }
            } else if (choice.equals("7")) {
                uo.logout();
//                crw.writeFile(uo);
                handle = false;
            } else {
                System.out.println("This is not an valid option, please give a number from 1 to 7.");
            }
//            crw.writeFile(uo);
        }
    }
}
