package Library;

import java.util.Scanner;

public class DeleteBook implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Delete Book --*--");

        String bookName;
        while (true) {
            System.out.print("Enter the book name you want to delete: ");
            bookName = scanner.nextLine().trim();

            if (bookName.isEmpty()) {
                System.out.println("Book name can't be empty.");
                continue;
            }
            break;
        }

        int bookIndex = database.getBook(bookName);
        if (bookIndex > -1) {

            System.out.print("\nAre you sure you want to delete '" + bookName + "'? (Yes/No): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                database.deleteBook(bookIndex);
                System.out.println("Book deleted successfully!");
            }
            else {
                System.out.println("Deletion cancelled.");
            }
        }
        else {
            System.out.println("Oops! Book doesn't exist.");
        }
    }
}