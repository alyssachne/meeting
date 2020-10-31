package UI;

import Usecase.UserOrganizer;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        UserOrganizer uo = new UserOrganizer();
        uo.createOrganizer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
//        System.out.println("Username is: " + username);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        System.out.println(uo.Login(username,password));

    }
}
