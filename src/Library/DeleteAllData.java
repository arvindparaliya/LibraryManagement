package Library;

import java.util.Scanner;

public class DeleteAllData implements IOOperation {

    @Override
    public void open(Database database, User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===*=== // Delete All Data // ===*===");
        System.out.println("WARNING: This will permanently DELETE all data from the library.");
        System.out.print("Do you wish to continue? (Yes/No): ");

        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            System.out.println("\nDeleting all data...");
            database.deleteAllData();

            System.out.println("All data has been deleted. System SUT-DOWN initiated.");
            new Exit().open(database, user);
        }
        else {
            System.out.println("Operation cancelled.");
            user.menu(database, user);
        }
    }
}
