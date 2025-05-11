package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Return Book --*--");

        String bookName;
        while (true) {
            System.out.print("Enter book name which you want to return: ");
            bookName = scanner.nextLine().trim();

            if (bookName.isEmpty()) {
                System.out.println(" Book name cannot be empty.");
                continue;
            }
            break;
        }

        Borrowing borrowingToReturn = null;
        Book bookToUpdate = null;
        int bookIndex = -1;

        for (Borrowing borrowing : database.getBrws()) {
            if (borrowing.getBook().getName().equalsIgnoreCase(bookName) &&
                    borrowing.getUser().getName().equals(user.getName())) {

                borrowingToReturn = borrowing;
                bookToUpdate = borrowing.getBook();
                bookIndex = database.getAllBooks().indexOf(bookToUpdate);
                break;
            }
        }

        if (borrowingToReturn == null) {
            System.out.println("You didn't borrow this book.");
            return;
        }

        int daysLate = borrowingToReturn.getDaysLeft();
        if (daysLate < 0) {
            double fine = Math.abs(daysLate) * 50;
            System.out.printf("\nWarning: This book is %d days overdue!%n", Math.abs(daysLate));
            System.out.printf("Fine to pay: $%.2f%n", fine);
        }

        bookToUpdate.setBrwcopies(bookToUpdate.getBrwcopies() + 1);
        database.returnBook(borrowingToReturn, bookToUpdate, bookIndex);

        System.out.println("\nBook returned successfully!");
        System.out.println("Thank you for returning \"" + bookName + "\" :)");
    }
}