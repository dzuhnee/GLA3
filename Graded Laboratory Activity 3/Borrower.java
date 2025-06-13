public class Borrower {
    private static int idCounter = 1;
    private int borrowerId;
    private String name;
    private Book[] borrowedBooks = new Book[10];
    private int borrowedCount = 0;

    private Transaction[] transactions = new Transaction[50];
    private int transactionCount = 0;

    public Borrower(String name) {
        this.borrowerId = idCounter++;
        this.name = name;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public String getName() {
        return name;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void borrowBook(Book book) {
        if (borrowedCount < borrowedBooks.length) {
            borrowedBooks[borrowedCount++] = book;
            addTransaction(book.getBookId(), book.getTitle(), "borrow");
            System.out.println("Book borrowed: " + book.getTitle());
        } else {
            System.out.println("Borrow limit reached. Cannot borrow more books.");
        }
    }

    public void returnBook(int bookId) {
        boolean found = false;
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i].getBookId() == bookId) {
                System.out.println("Book returned: " + borrowedBooks[i].getTitle());
                addTransaction(borrowedBooks[i].getBookId(), borrowedBooks[i].getTitle(), "return");
                for (int j = i; j < borrowedCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }
                borrowedBooks[--borrowedCount] = null;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book with ID " + bookId + " not found in borrowed list.");
        }
    }

    public void viewBorrowedBooks() {
        if (borrowedCount == 0) {
            System.out.println(name + " has not borrowed any books.");
            return;
        }
        System.out.printf("Books borrowed by %s (Borrower ID: %d):%n", name, borrowerId);
        System.out.printf("%-10s %-50s %-20s %-20s %-5s%n", "Book ID", "Title", "Author First Name", "Author Last Name",
                "Year");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < borrowedCount; i++) {
            borrowedBooks[i].displayBook();
        }
    }

    private void addTransaction(int bookId, String bookName, String type) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = new Transaction(bookId, bookName, type);
        }
    }

    public void viewTransactions() {
        if (transactionCount == 0) {
            System.out.println(name + " has no transactions.");
            return;
        }
        System.out.printf("Transactions for %s (Borrower ID: %d):%n", name, borrowerId);
        System.out.printf("%-5s %-10s %-30s %-12s %-10s %-10s%n", "ID", "Book ID", "Book Name", "Date", "Time", "Type");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        for (int i = 0; i < transactionCount; i++) {
            transactions[i].displayTransaction();
        }
    }
}