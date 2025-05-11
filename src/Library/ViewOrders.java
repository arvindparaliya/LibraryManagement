package Library;

import java.util.Scanner;

public class ViewOrders implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- View Orders --*--");


        String bookName;
        while (true) {
            System.out.print("Enter book name: ");
            bookName = scanner.nextLine().trim();

            if (bookName.isEmpty()) {
                System.out.println("Book name cannot be empty.");
                continue;
            }
            break;
        }

        int bookIndex = database.getBook(bookName);
        if (bookIndex == -1) {
            System.out.println("Book \"" + bookName + "\" doesn't exist.");
            return;
        }

        viewOrders(bookName, database);
    }

    private void viewOrders(String bookName, Database database) {
        System.out.println("\n===*== Orders for " + bookName + " ==*===");

        String format = "%-30s  | %-20s  | %6s  | %10s%n" ;
        String separator = "*=================================================================================*";

        System.out.printf(format, "User", "Book", "Qty", "Price");
        System.out.println(separator);

        boolean foundOrders = false;

        for (Order order : database.getAllOrders()) {
            if (order.getBook().getName().equalsIgnoreCase(bookName)) {
                System.out.printf(format,
                        truncate(order.getUser().getName(), 30),
                        truncate(order.getBook().getName(), 20),
                        order.getQty(),
                        String.format("$%.2f", order.getPrice()));
                foundOrders = true;
            }
        }

        if (!foundOrders) {
            System.out.println("No orders found for this book.");
        }

        System.out.println("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    private String truncate(String text, int maxLength) {

        if (text.length() <= maxLength)
            return text;
        return text.substring(0, maxLength-3) + " ";
    }
}