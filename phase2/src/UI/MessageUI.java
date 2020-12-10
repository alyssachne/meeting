package UI;

import Controller.ControllerFacade;
import Gateway.ControllerRW;

import java.util.Scanner;

public class MessageUI {

    public void MessageDemo(){
        ControllerRW crw = new ControllerRW();
        ControllerFacade uo = null;
        if (crw.readFile()!=null){
            uo = crw.readFile();
        }else {
            System.out.println("Read File Error");
        }

        Scanner scanner = new Scanner(System.in);
            System.out.println("1.Unread message");
            System.out.println("2.Read message");
            System.out.println("3.Archived message");
            System.out.println("Please choose which box you want to access");
            String box = scanner.nextLine();
            if (box.equals("1")) {
                System.out.println("Here is a list of contacts that messages are unread");
                uo.checkContacts("Unread");
                System.out.println("Please enter the username to check the message");
                String sender = scanner.nextLine();
                uo.getMessage(sender,"Unread");
                System.out.print("These messages are marked as read! If you want to delete these message, please go to Read Message Box");
            }else if (box.equals("2")){
                System.out.println("Here is a list of contacts that messages are unread");
                uo.checkContacts("Read");
                String sender = scanner.nextLine();
                uo.getMessage(sender,"Read");
                System.out.println("Please choose the message number you want to modify(you can enter exit to skip this option)");
                String modifyOption = scanner.nextLine();
                if (!modifyOption.equalsIgnoreCase("exit")){
                    System.out.println("1.Delete this message");
                    System.out.println("2.Archive this message");
                    System.out.println("3.Mark as unread");
                    String modify = scanner.nextLine();
                    if (modify.equals("1")){
                        uo.deleteMessage(sender,modifyOption,"Read");//need to modify
                    }else if (modify.equals("2")){
                        uo.archiveMessage(sender, modifyOption);//need to modify
                    }else if (modify.equals("3")){
                        uo.markAsUnread(sender,modifyOption);//need to modify
                    }else{
                        System.out.println("This is not an valid option, please give a number from 1 to 3.");
                    }
                }
            }else if (box.equals("3")){
                System.out.println("Here is a list of contacts that messages are Archived");
                uo.checkContacts("Archived");
                String sender = scanner.nextLine();
                uo.getMessage(sender,"Archived");
                System.out.println("Please choose the message number you want to modify(you can enter exit to skip this option)");
                String modifyOption = scanner.nextLine();
                if (!modifyOption.equalsIgnoreCase("exit")){
                    System.out.println("1.Delete this message");
                    System.out.println("2.Mark as unread");
                    String modify = scanner.nextLine();
                    if (modify.equals("1")){
                        uo.deleteMessage(sender,modifyOption,"Read");//need to modify
                    }else if (modify.equals("2")){
                        uo.markAsUnread(sender,modifyOption);//need to modify
                    }else{
                        System.out.println("This is not an valid option, please give a number from 1 to 2.");
                    }
                }
            }else{
                System.out.println("This is not an valid option, please give a number from 1 to 3.");
            }
        crw.writeFile(uo);
    }
}
