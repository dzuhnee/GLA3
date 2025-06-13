import java.util.Scanner;

public class LibraryApp {
    private static Book[] books = new Book[100];
    private static int bookCount = 0;
    private static Borrower[] borrowers = new Borrower[50];
    private static int borrowerCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleData();

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    addNewBorrower();
                    break;
                case 5:
                    viewAllBorrowers();
                    break;
                case 6:
                    deleteBorrower();
                    break;
                case 7:
                    listAllTransactions();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1–8.");
            }
            System.out.println();
        } while (choice != 8);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n1. Add New Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Add New Borrower");
        System.out.println("5. View All Borrowers");
        System.out.println("6. Delete Borrower");
        System.out.println("7. List All Transactions");
        System.out.println("8. Exit");
    }

    private static void initializeSampleData() {
        books[bookCount++] = new Book("Tuesdays with Morrie", "Mitch", "Albom", 1997);
        books[bookCount++] = new Book("Surrounded by Idiots", "Thomas", "Erikson", 2014);
        books[bookCount++] = new Book("Everything I Know About Love", "Dolly", "Alderton", 2018);
        books[bookCount++] = new Book("A Little Life", "Hanya", "Yanahigara", 2015);
        books[bookCount++] = new Book("The Let Them Theory", "Mel", "Robbins", 2024);
        books[bookCount++] = new Book("Atomic Habits", "James", "Clear", 2018);
        books[bookCount++] = new Book("The Midnight Library", "Matt", "Haig", 2020);
        books[bookCount++] = new Book("Educated", "Tara", "Westover", 2018);
        books[bookCount++] = new Book("The Book Thief", "Markus", "Zusak", 2005);
        books[bookCount++] = new Book("Interlinked", "Mark", "Manson", 2016);


        borrowers[borrowerCount++] = new Borrower("Nathalie Lim-Cheng");
        borrowers[borrowerCount++] = new Borrower("Oliver Berris");
        borrowers[borrowerCount++] = new Borrower("Gary Soriano");

        borrowers[0].borrowBook(books[0]);
        borrowers[0].borrowBook(books[1]);
        borrowers[0].borrowBook(books[2]);

        borrowers[1].borrowBook(books[3]);
        borrowers[1].borrowBook(books[4]);
        borrowers[1].borrowBook(books[5]);

        borrowers[2].borrowBook(books[6]);
        borrowers[2].borrowBook(books[7]);
        borrowers[2].borrowBook(books[8]);
        borrowers[2].borrowBook(books[9]);
    }

    private static void addNewBook() {
        if (bookCount >= books.length) {
            System.out.println("Maximum number of books reached.");
            return;
        }
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter author last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        books[bookCount++] = new Book(title, firstName, lastName, year);
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books have been added yet.");
            return;
        }

        System.out.printf("%-10s %-50s %-20s %-20s %-5s%n","Book ID", "Title", "Author First", "Author Last", "Year");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < bookCount; i++) {
            books[i].displayBook();
        }
    }

    private static void searchBook() {
        if (bookCount == 0) {
            System.out.println("No books exist to search.");
            return;
        }
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        System.out.printf("%-10s %-50s %-20s %-20s %-5s%n","Book ID", "Title", "Author First", "Author Last", "Year");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            if (b.getTitle().toLowerCase().contains(searchTerm)
                    || b.getAuthorFirstName().toLowerCase().contains(searchTerm)
                    || b.getAuthorLastName().toLowerCase().contains(searchTerm)) {
                b.displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching \"" + searchTerm + "\".");
        }
    }

    private static void addNewBorrower() {
        if (borrowerCount >= borrowers.length) {
            System.out.println("Maximum number of borrowers reached.");
            return;
        }

        System.out.print("Enter borrower name: ");
        String name = scanner.nextLine();

        borrowers[borrowerCount++] = new Borrower(name);
        System.out.println("Borrower added successfully!");
        borrowerSubMenu(borrowers[borrowerCount - 1]);

    }

    private static void viewAllBorrowers() {
        if (borrowerCount == 0) {
            System.out.println("No borrowers have been added yet.");
            return;
        }

        System.out.printf("%-12s %-30s%n", "Borrower ID", "Name");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < borrowerCount; i++) {
            System.out.printf("%-12d %-30s%n",
                    borrowers[i].getBorrowerId(),
                    borrowers[i].getName());
        }
        System.out.print("\nEnter borrower ID (or 0 to go back): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id == 0) {
            return;
        }

        Borrower b = findBorrowerById(id);
        if (b != null) {
            borrowerSubMenu(b);
        } else {
            System.out.println("No borrower with ID " + id + ".");
        }
    }

    private static void deleteBorrower() {
        if (borrowerCount == 0) {
            System.out.println("No borrowers exist.");
            return;
        }

        System.out.printf("%-12s %-30s%n", "Borrower ID", "Name");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < borrowerCount; i++) {
            System.out.printf("%-12d %-30s%n",
                    borrowers[i].getBorrowerId(),
                    borrowers[i].getName());
        }

        System.out.print("\nEnter borrower ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < borrowerCount; i++) {
            if (borrowers[i].getBorrowerId() == id) {
                Borrower toDelete = borrowers[i];
                toDelete.returnAllBooks();

                for (int j = i; j < borrowerCount - 1; j++) {
                    borrowers[j] = borrowers[j + 1];
                }
                borrowers[--borrowerCount] = null;
                found = true;
                System.out.println("Borrower deleted successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Borrower with ID " + id + " not found.");
        }
    }

    public static void borrowerSubMenu(Borrower borrower){
        int choice;
        do {
            System.out.println();
            System.out.println("--- Borrower Menu ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Borrowed Books");
            System.out.println("4. View Transaction History");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    borrowBook(borrower);
                    break;
                case 2:
                    returnBook(borrower);
                    break;
                case 3:
                    borrower.viewBorrowedBooks();
                    break;
                case 4:
                    borrower.viewTransactions();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void listAllTransactions() {
        boolean hasTransactions = false;

        for (int i = 0; i < borrowerCount; i++) {
            Borrower borrower = borrowers[i];
            if (borrower.getTransactionCount() > 0) {
                hasTransactions = true;
                borrower.viewTransactions();
                System.out.println();
            }
        }

        if (!hasTransactions) {
            System.out.println("No transactions found.");
        }
    }

    private static void borrowBook(Borrower borrower) {
        if (bookCount == 0) {
            System.out.println("No books exist in the library to borrow.");
            return;
        }

        viewAllBooks();
        System.out.print("\nEnter book ID to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (borrower.hasBook(bookId)) {
            System.out.println("You already borrowed this book.");
        } else {
            borrower.borrowBook(book);
        }

    }

    private static void returnBook(Borrower borrower) {
        borrower.viewBorrowedBooks();
        System.out.print("\nEnter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        borrower.returnBook(bookId);
    }

    private static Book findBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId() == id) {
                return books[i];
            }
        }
        return null;
    }

    private static Borrower findBorrowerById(int id){
        for (int i = 0; i < borrowerCount; i++) {
            if (borrowers[i].getBorrowerId() == id) {
                return borrowers[i];
            }
        }
        return null;
    }
}