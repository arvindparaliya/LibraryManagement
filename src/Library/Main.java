package Library;

import java.util.Scanner;

public class Main {

    static Scanner sc;
    static Database database;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        database = new Database();

        boolean start = true;
        while (start) {
            System.out.println("\n==*== Library Management System ==*==");
            System.out.println("1. Login");
            System.out.println("2. Create New User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    newUser();
                    break;
                case 3:
                    start = false;
                    System.out.println("Exiting system...");
                    break;
            }
        }
        sc.close();
    }

    private static void login() {

        System.out.println("\n=== Login ===");
        System.out.print("Enter your phone number: ");

        String phoneNumber = sc.nextLine();

        while (phoneNumber.isEmpty()) {
            System.out.println("Phone number can't be empty!");
            System.out.print("Enter your phone number: ");
            phoneNumber = sc.nextLine();
        }

        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        while (email.isEmpty()) {
            System.out.println("Email can't be empty!");
            System.out.print("Enter your email: ");
            email = sc.nextLine();
        }

        int n = database.login(phoneNumber, email);
        if (n != -1) {
            User user = database.getUser(n);
            user.menu(database, user);
        }
        else {
            System.out.println("User doesn't exist!");
        }
    }

    private static void newUser() {
        System.out.println("\n=== Create A New Account ===");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            System.out.print("Enter your name: ");
            name = sc.nextLine();
        }

        if (database.userExists(name)) {
            System.out.println("Username Already exists! Please try another one.");
            return;
        }

        System.out.print("Enter your phone number: ");
        String phoneNumber = sc.nextLine();

        while (phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty!");
            System.out.print("Enter your phone number: ");
            phoneNumber = sc.nextLine();
        }

        System.out.print("Enter your email Id: ");
        String email = sc.nextLine();
        while (email.isEmpty()) {
            System.out.println("Email can't be empty!");
            System.out.print("Enter your email Id: ");
            email = sc.nextLine();
        }

        System.out.println("\nSelect your account type:");
        System.out.println("1. Admin");
        System.out.println("2. Normal User");
        System.out.print("Enter choice (1-2): ");
        
        int accountType = getIntInput(1, 2);

        User user;
        if (accountType == 1) {
            user = new Admin(name, email, phoneNumber);
        }
        else {
            user = new NormalUser(name, email, phoneNumber);
        }

        database.AddUser(user);
        System.out.println("Account created successfully!");
        user.menu(database, user);
    }

    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } 
            catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}