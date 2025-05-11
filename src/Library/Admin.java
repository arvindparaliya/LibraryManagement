package Library;

import java.util.Scanner;

public class Admin extends User {

    public Admin(String name) {
        super(name);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    public Admin(String name, String email, String phoneNumber ) {
        super(name, email, phoneNumber);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==*== Admin Menu ==*==");

            System.out.println("1. View Books");
            System.out.println("2. Add Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search");
            System.out.println("5. Delete All Data");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            int choice = getIntInput(scanner, 1, 7);
            operations[choice-1].open(database, user);
        }
    }

    private int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Please enter the number between " + min + " and " + max + ": ");
            }
            catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter the number: ");
            }
        }
    }

    public String toString() {

        return name+"<N/>"+email+"<N/>"+phoneNumber+"<N/>"+"Admin";
    }
}