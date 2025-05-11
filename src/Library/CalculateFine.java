package Library;

import java.util.Scanner;

public class CalculateFine implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Calculate Fine --*--");

        String bookName = getValidBookName(scanner);

        boolean bookFound = false;

        for (Borrowing borrowing : database.getBrws()) {
            if (borrowing.getBook().getName().equals(bookName) && borrowing.getUser().getName().equals(user.getName())) {

                bookFound = true;
                int daysLate = borrowing.getDaysLeft();

                if (daysLate > 0) {
                    double fine = daysLate * 50;
                    System.out.println("\nYou are late!");
                    System.out.printf("You have to pay a fine of $%.2f%n", fine);
                } else {
                    System.out.println("\nYou don't need to pay any fine. You're on time!");
                }
                break;
            }
        }

        if (!bookFound) {
            System.out.println("\nYou haven't borrowed this book!");
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private String getValidBookName(Scanner scanner) {
        String bookName;
        do {
            System.out.print("Enter book name: ");
            bookName = scanner.nextLine().trim();
            if (bookName.isEmpty()) {
                System.out.println("Book name can't be empty. Please try again.");
            }
        }
        while (bookName.isEmpty());

        return bookName;
    }
}
