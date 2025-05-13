# Library Management System

This is a console-based Library Management System written in Java OOPs concepts. It allows users and admins to manage books, borrow and return them, and perform basic operations like adding, deleting, searching, and viewing books.


## Features

- **Admin functionalities:**
    - View all books
    - Add new books
    - Delete existing books
    - Search for books
    - View borrowing orders
    - Delete all data

- **User functionalities:**
    - Register and login
    - View available books
    - Borrow books
    - Return books
    - Check and calculate fine for late return

## Technologies Used

- Java (Core + OOP concepts)
- File handling for storing book and user data
- text-based user interface (Console I/O)

## How to Run

1. Open the project in any Java IDE (e.g.,IntelliJ IDEA, VS Code).
2. Make sure all `.txt` files (`books.txt`, `users.txt`, `borrowings.txt`, `orders.txt`) are present in the root directory.
3. Run the `Main.java` file.
4. Follow the on-screen instructions to register, login, or manage the library.

## Contributing

Contributions are welcome! If you find a bug or have a feature request, feel free to open an issue or submit a pull request.
   

## Notes

- Data is stored in `.txt` files, so the app can keep data between sessions.
- Admin and user functionalities are separated using interface-based design (IOOperation).
- this project help to understand OOP and file handling concepts.
