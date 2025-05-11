package Library;

import java.util.Scanner;

public class NormalUser extends User {

    public NormalUser(String name) {
        super(name);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public NormalUser(String name, String email, String phoneNumber ) {
        super(name, email, phoneNumber );
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }



    @Override
    public void menu(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        String[] menuItems = {
                "View Books",
                "Search",
                "Place Order",
                "Borrow Book",
                "Calculate Fine",
                "Return Book",
                "Exit"
        };

        while (true) {
            System.out.println("\n--*-- Normal User Menu --*--");

            for (int i = 0; i < menuItems.length; i++) {
                System.out.println((i+1) + ". " + menuItems[i]);
            }

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
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public String toString() {
        return name +"<N/>"+ email+ "<N/>"+ phoneNumber +"<N/>"+"Normal";
    }
}