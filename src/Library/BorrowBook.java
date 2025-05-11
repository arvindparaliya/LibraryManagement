package Library;

import java.util.Scanner;

public class BorrowBook implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Borrow Book --*--");


        String bookName;
        while (true) {
            System.out.print("Enter book name: ");

            bookName = scanner.nextLine();
            if (bookName.isEmpty()) {
                System.out.println("Book name can't be empty.");
                continue;
            }
            break;
        }

        int bookIndex = database.getBook(bookName);

        if (bookIndex < 0) {
            System.out.println("Book doesn't exist.");
            return;
        }

        Book book = database.getBook(bookIndex);

        for (Borrowing b : database.getBrws()) {

            if (b.getBook().getName().equals(bookName) &&
                    b.getUser().getName().equals(user.getName())) {
                System.out.println("You have borrowed this book before.");
                return;
            }
        }

        if (book.getBrwcopies() < 1) {
            System.out.println("Sorry! This book isn't available for borrowing.");
            return;
        }

        Borrowing borrowing = new Borrowing(book, user);
        book.setBrwcopies(book.getBrwcopies() - 1);
        database.borrowBook(borrowing, book, bookIndex);

        System.out.println("\nBorrowing successfully!");
        System.out.println("You must return the book before 14 days from now.");
        System.out.println("Expiry date: " + borrowing.getFinish());
        System.out.println("Enjoy your reading :) ");
    }
}
