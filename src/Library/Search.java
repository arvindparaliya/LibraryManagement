package Library;

import java.util.Scanner;

public class Search implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Search Book --*--");

        String bookName;
        while (true) {
            System.out.print("Enter the book name which you want to find : ");
            bookName = scanner.nextLine().trim();

            if (bookName.isEmpty()) {
                System.out.println(" Book name cannot be empty.");
                continue;
            }
            break;
        }

        int bookIndex = database.getBook(bookName);

        if (bookIndex > -1) {
            Book foundBook = database.getBook(bookIndex);
            System.out.println("\nBook found.");
            System.out.println(foundBook.toString());
        }
        else {
            System.out.println("\nBook \"" + bookName + "\" doesn't exist!");
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}