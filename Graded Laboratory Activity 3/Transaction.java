import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int idCounter = 1;
    private int transactionId;
    private int bookId;
    private String bookName;
    private LocalDate date;
    private LocalTime time;
    private String transactionType;

    public Transaction(int bookId, String bookName, String transactionType) {
        this.transactionId = idCounter++;
        this.bookId = bookId;
        this.bookName = bookName;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.transactionType = transactionType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeFormatted() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getTransactionType() {
        return transactionType;
    }

public void displayTransaction() {
String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
System.out.printf("%-5d %-10d %-30s %-12s %-10s %-10s%n",
36 transactionId, bookId, bookName, date, formattedTime, transactionType);
}
}
