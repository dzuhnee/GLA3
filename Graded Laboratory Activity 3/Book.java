public class Book {
    private static int idCounter = 1;
    private int bookId;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int publicationYear;

    public Book(String title, String authorFirstName, String authorLastName, int publicationYear) {
        this.bookId = idCounter++;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publicationYear = publicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void displayBook() {
        System.out.printf("%-10d %-50s %-20s %-20s %-5d%n", bookId, title, authorFirstName, authorLastName,
                publicationYear);
    }
}

// test