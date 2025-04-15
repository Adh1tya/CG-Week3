import java.util.Scanner;
class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}
class Library {
    private Book head, tail;

    public void addAtBeginning(Book newBook) {
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addAtEnd(Book newBook) {
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addAtPosition(Book newBook, int position) {
        if (position <= 0 || head == null) {
            addAtBeginning(newBook);
            return;
        }
        Book current = head;
        int index = 0;
        while (current != null && index < position - 1) {
            current = current.next;
            index++;
        }
        if (current == null || current.next == null) {
            addAtEnd(newBook);
        } else {
            newBook.next = current.next;
            newBook.prev = current;
            current.next.prev = newBook;
            current.next = newBook;
        }
    }

    public void removeBook(int bookId) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Book with ID " + bookId + " removed.");
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID not found.");
    }

    public void searchByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                displayBook(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Book with title \"" + title + "\" not found.");
    }

    public void searchByAuthor(String author) {
        Book current = head;
        boolean found = false;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                displayBook(current);
                found = true;
            }
            current = current.next;
        }
        if (!found)
            System.out.println("No books found by author \"" + author + "\".");
    }

    public void updateAvailability(int bookId, boolean status) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = status;
                System.out.println("Availability updated for book ID " + bookId);
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID not found.");
    }

    public void displayForward() {
        System.out.println("Books in Library (Forward):");
        Book current = head;
        if (current == null) {
            System.out.println("No books available.");
        }
        while (current != null) {
            displayBook(current);
            current = current.next;
        }
    }

    public void displayReverse() {
        System.out.println("Books in Library (Reverse):");
        Book current = tail;
        if (current == null) {
            System.out.println("No books available.");
        }
        while (current != null) {
            displayBook(current);
            current = current.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        Book current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private void displayBook(Book b) {
        System.out.println("ID: " + b.bookId + ", Title: " + b.title + ", Author: " + b.author +
                ", Genre: " + b.genre + ", Available: " + (b.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Update Availability");
            System.out.println("6. Display All Books (Forward)");
            System.out.println("7. Display All Books (Reverse)");
            System.out.println("8. Count Total Books");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = sc.nextBoolean();
                    System.out.println("Insert at: 1. Beginning  2. End  3. Position");
                    int posChoice = sc.nextInt();
                    Book newBook = new Book(title, author, genre, id, isAvailable);
                    switch (posChoice) {
                        case 1:
                            library.addAtBeginning(newBook);
                            break;
                        case 2:
                            library.addAtEnd(newBook);
                            break;
                        case 3:
                            System.out.print("Enter position (0-based index): ");
                            int pos = sc.nextInt();
                            library.addAtPosition(newBook, pos);
                            break;
                        default:
                            System.out.println("Invalid position choice.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = sc.nextInt();
                    library.removeBook(removeId);
                    break;

                case 3:
                    System.out.print("Enter Title to search: ");
                    String searchTitle = sc.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 4:
                    System.out.print("Enter Author to search: ");
                    String searchAuthor = sc.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;

                case 5:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Set availability (true/false): ");
                    boolean newStatus = sc.nextBoolean();
                    library.updateAvailability(updateId, newStatus);
                    break;

                case 6:
                    library.displayForward();
                    break;

                case 7:
                    library.displayReverse();
                    break;

                case 8:
                    System.out.println("Total books in library: " + library.countBooks());
                    break;

                case 9:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 9);

        sc.close();
    }
}
