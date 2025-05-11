package Library;

public class ViewBooks implements IOOperation {

    @Override
    public void open(Database database, User user) {
        System.out.println("\n--*-- Book List --*--");

        String format = "%-30s | %-20s | %-20s | %-10s | %4s | %8s | %6s%n";
        String separator = "*===================================================================================*";

        System.out.printf(format,
                "Name", "Author", "Publisher", "CLA", "Qty", "Price", "Brw cps");
        System.out.println(separator);

        for (Book book : database.getAllBooks()) {
            System.out.printf(format,
                    truncate(book.getName(), 29),
                    truncate(book.getAuthor(), 21),
                    truncate(book.getPublisher(), 20),
                    truncate(book.getAddress(), 11),
                    book.getQty(),
                    String.format("$%.2f", book.getPrice()),
                    book.getBrwcopies());
        }
        if (database.getAllBooks().isEmpty()) {
            System.out.println("There are no any books available in the library");
        }
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength)
            return text;
        return text.substring(0, maxLength-3) + " ";
    }
}