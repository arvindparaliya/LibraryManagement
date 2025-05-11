package Library;

import java.util.Scanner;

public class Exit implements IOOperation {

    private Database database;
    private Scanner scanner;

    @Override
    public void open(Database database, User user) {
        this.database = database;
        this.scanner = new Scanner(System.in);

        showMainMenu();
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n==*== // Library Management System // ==*==");
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
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;
            }
        }
    }

    private void login() {
        System.out.println("\n--*-- Login --*--");

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        while (phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            System.out.print("Enter phone number: ");
             phoneNumber= scanner.nextLine();
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        while (email.isEmpty()) {
            System.out.println("Email can't be empty.");
            System.out.print("Enter email: ");
            email = scanner.nextLine();
        }
        
        int userId = database.login(phoneNumber, email);
        if (userId != -1) {
            User user = database.getUser(userId);
            user.menu(database, user);
        }
        else {
            System.out.println("User doesn't exist.");
        }
    }

    private void newUser() {
        System.out.println("\n--*-- Create New Account --*--");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter name: ");
            name = scanner.nextLine();
        }

        if (database.userExists(name)) {
            System.out.println("Username already exists. Please try another one.");
            return;
        }

        System.out.print("Enter phone number: ");
        String phoneNumber  = scanner.nextLine();
        while (phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            System.out.print("Enter phone number: ");
             phoneNumber= scanner.nextLine();
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        while (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            System.out.print("Enter email: ");
            email = scanner.nextLine();
        }

        System.out.println("\nSelect account type:");
        System.out.println("1. Admin");
        System.out.println("2. Normal User");
        System.out.print("Enter choice (1-2): ");
        int accountType = getIntInput(1, 2);

        User user;
        if (accountType == 1) {
            user = new Admin(name, email, phoneNumber );
        }
        else {
            user = new NormalUser(name, email, phoneNumber );
        }

        database.AddUser(user);
        System.out.println("Account created successfully!");
        user.menu(database, user);
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());

                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Please enter the number between " + min + " and " + max + ": ");
            }
            catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}