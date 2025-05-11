package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==*== Add New Book ==*==");

        String name = getNonExistingBookName(database, scanner);
        String author = getNonEmptyInput("Enter author: ", "Author", scanner);
        String publisher = getNonEmptyInput("Enter publisher: ", "Publisher", scanner);
        String address = getNonEmptyInput("Enter collection address: ", "Collection address", scanner);
        int qty = getValidInteger("Enter quantity: ", "Quantity", scanner);
        double price = getValidDouble("Enter price: ", "Price", scanner);
        int brwCopies = getValidInteger("Enter borrowing copies: ", "Borrowing copies", scanner);

        Book book = new Book(name, author, publisher, address, qty, price, brwCopies);
        database.AddBook(book);
        System.out.println("\nBook added successfully!");
    }

    private String getNonExistingBookName(Database database, Scanner scanner) {
        while (true) {
            System.out.print("Enter book name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Book name can't be empty.");
                continue;
            }
            if (database.getBook(name) != -1) {
                System.out.println("Book already exists.");
                continue;
            }
            return name;
        }
    }

    private String getNonEmptyInput(String prompt, String fieldName, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: " + fieldName + " cannot be empty.");
                continue;
            }
            return input;
        }
    }

    private int getValidInteger(String prompt, String fieldName, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be empty.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.println(fieldName + " must be an integer.");
            }
        }
    }

    private double getValidDouble(String prompt, String fieldName, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be empty!");
                continue;
            }
            try {
                return Double.parseDouble(input);
            }
            catch (NumberFormatException e) {
                System.out.println(fieldName + " must be a number!");
            }
        }
    }
}