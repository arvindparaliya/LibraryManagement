package Library;

import java.util.Scanner;

public class PlaceOrder implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--*-- Place Order --*--");

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

        int qty;
        while (true) {
            System.out.print("Enter quantity: ");
            String qtyInput = scanner.nextLine().trim();
            try {
                qty = Integer.parseInt(qtyInput);
                if (qty <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Quantity must be a whole number.");
            }
        }

        int bookIndex = database.getBook(bookName);
        if (bookIndex == -1) {
            System.out.println("Oops! Book \"" + bookName + "\" doesn't exist!");
            return;
        }

        Book book = database.getBook(bookIndex);
        if (book.getQty() < qty) {
            System.out.println("Error : There are no enough stock, Available quantity: " + book.getQty());
            return;
        }

        Order order = new Order();
        order.setBook(book);
        order.setUser(user);
        order.setQty(qty);
        order.setPrice(book.getPrice() * qty);

        book.setQty(book.getQty() - qty);
        database.addOrder(order, book, bookIndex);

        System.out.println("\nOrder placed successfully!");
        System.out.printf("Total price: ", order.getPrice());
        System.out.println("Remaining stock: " + book.getQty());
    }
}