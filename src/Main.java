import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String LIBRARY_NAME = "VOID CENTURY LIBRARY";
    private static final int MAX_BOOKS_PER_PERSON = 3;
    private static final int STUDENT_BORROW_DAYS = 10;
    private static final int TEACHER_BORROW_DAYS = 60; // 2 months
    private static final double LATE_RETURN_FINE_PER_DAY = 25.0;
    private static final int TOTAL_LOCKERS = 20;

    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static List<Book> bookInventory = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Locker> lockers = new ArrayList<>();

    public static void main(String[] args) {
        
    	initializeSystem();
        showWelcomeScreen();

        
        boolean running = true;
       
        while (running) {
            if (currentUser == null) {
                running = loginMenu();
            } else if (currentUser instanceof Admin) {
                adminMenu();
            } else if (currentUser instanceof Student) {
                studentMenu();
            } else if (currentUser instanceof Teacher) {
                teacherMenu();
            }
        }
    }

    private static void initializeSystem() {
    	// admin account
          users.add(new Admin("sayem", "sayem123"));

        // sample users
          users.add(new Student("232-15-854", "sayem123", "sayem", "CSE"));
          users.add(new Student("232-15-013", "hridoy123", "hridoy", "CSE"));
          users.add(new Student("232-15-350", "obaydul123", "obaydul", "CSE"));
        
            users.add(new Teacher("ashaf", "asahf123", "Md. Ashaf Uddaula", "CSE"));

        //  sample book
        bookInventory.add(new Book("978-0-306-40615-7", "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science", 2009, 5));
        bookInventory.add(new Book("978-0-7356-5227-3", "Clean Code", "Robert C. Martin", "Software Engineering", 2008, 3));
        bookInventory.add(new Book("978-0-13-235088-4", "Effective Java", "Joshua Bloch", "Programming", 2018, 4));

        // Initialize lockers
      
        for (int i = 1; i <= TOTAL_LOCKERS; i++) {
            lockers.add(new Locker(i));
         }
        
    }

    //************************************************************************
    private static void showWelcomeScreen() {
	        System.out.println("====================================");
	        System.out.println("Welcome to " + LIBRARY_NAME);
	        System.out.println("====================================");
    }

    //***********************************************************************
    
    private static boolean loginMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Exit System");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
	            case 1:
	                login();
	                return true;
	            case 2:
	                System.out.println("Thank you for using " + LIBRARY_NAME + ". Goodbye!");
	                return false;
	            default:
	                System.out.println("Invalid option. Please try again.");
	                return true;
        }
    }

    
    
    private static void login() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + currentUser.getId());
                return;
            }
        }

        System.out.println("Invalid ID or password. Please try again.");
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    private static void adminMenu() {
	        System.out.println("\n===== Admin Menu =====");
	        System.out.println("1. Add Book");
	        System.out.println("2. Delete Book");
	        System.out.println("3. View All Books");
	        System.out.println("4. View All Users");
	        System.out.println("5. Check Fines");
	        System.out.println("6. Add User");
	        System.out.println("7. View All Lockers");
	        System.out.println("8. Logout");
	        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                viewAllBooks();
                break;
            case 4:
                viewAllUsers();
                break;
            case 5:
                checkAllFines();
                break;
            case 6:
                addUser();
                break;
            case 7:
                viewAllLockers();
                break;
            case 8:
                logout();
                break;
            default:
            		System.out.println("Invalid option. Please try again.");
        }
    }

    private static void studentMenu() {
	        Student student = (Student) currentUser;
	        System.out.println("\n===== Student Menu =====");
	        System.out.println("1. Search Book");
	        System.out.println("2. Borrow Book");
	        System.out.println("3. Return Book");
	        System.out.println("4. View My Books");
	        System.out.println("5. Check My Fines");
	        System.out.println("6. Rent Locker");
	        System.out.println("7. Release Locker");
	        System.out.println("8. Logout");
	        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchBook();
                break;
            case 2:
                borrowBook(student);
                break;
            case 3:
                returnBook(student);
                break;
            case 4:
                viewMyBooks(student);
                break;
            case 5:
                checkMyFines(student);
                break;
            case 6:
                rentLocker(student);
                break;
            case 7:
                releaseLocker(student);
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void teacherMenu() {
	        Teacher teacher = (Teacher) currentUser;
	        System.out.println("\n===== Teacher Menu =====");
	        System.out.println("1. Search Book");
	        System.out.println("2. Borrow Book");
	        System.out.println("3. Return Book");
	        System.out.println("4. View My Books");
	        System.out.println("5. Check My Fines");
	        System.out.println("6. Rent Locker");
	        System.out.println("7. Release Locker");
	        System.out.println("8. Logout");
	        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                	searchBook();
                break;
            case 2:
            		borrowBook(teacher);
                	break;
            case 3:
                returnBook(teacher);
                break;
            case 4:
                viewMyBooks(teacher);
                break;
            case 5:
                checkMyFines(teacher);
                break;
            case 6:
                rentLocker(teacher);
                break;
            case 7:
                releaseLocker(teacher);
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Admin Functions
    private static void addBook() {
        System.out.println("\n=== Add Book ===");

        System.out.print("Enter ISBN (format: 978-x-xxx-xxxxx-x): ");
        String isbn = scanner.nextLine();

        // Valid ISBN 
        if (!isbn.matches("\\d{3}-\\d-\\d{3}-\\d{5}-\\d")) {
            System.out.println("Invalid ISBN format! Please follow the international standard format.");
            return;
        }

        //  ISBN already exists
        for (Book book : bookInventory) {
            if (book.getIsbn().equals(isbn)) {
                System.out.println("A book with this ISBN already exists!");
                return;
            }
        }

	        System.out.print("Enter Title: ");
	        String title = scanner.nextLine();
	
	        System.out.print("Enter Author: ");
	        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Publication Year: ");
        int year = getIntInput();

        System.out.print("Enter Number of Copies: ");
        int copies = getIntInput();

        Book newBook = new Book(isbn, title, author, category, year, copies);
        bookInventory.add(newBook);

        System.out.println("Book added successfully!");
    }

    private static void deleteBook() {
        System.out.println("\n=== Delete Book ===");

        System.out.print("Enter ISBN of book to delete: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < bookInventory.size(); i++) {
            if (bookInventory.get(i).getIsbn().equals(isbn)) {
                bookInventory.remove(i);
                System.out.println("Book deleted successfully!");
                return;
            }
        }

        System.out.println("Book with ISBN " + isbn + " not found!");
    }

    private static void viewAllBooks() {
        System.out.println("\n=== All Books ===");

        if (bookInventory.isEmpty()) {
            System.out.println("No books in the inventory.");
            return;
        }

        for (Book book : bookInventory) {
            System.out.println(book);
            System.out.println("----------------------------------------");
        }
    }

    private static void viewAllUsers() {
        System.out.println("\n=== All Users ===");

        if (users.isEmpty()) {
            System.out.println("No users in the system.");
            return;
        }

        for (User user : users) {
            if (user instanceof Admin) {
                System.out.println("Admin ID: " + user.getId());
            } else if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("Student ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Department: " + student.getDepartment());
            } else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                System.out.println("Teacher ID: " + teacher.getId());
                System.out.println("Name: " + teacher.getName());
                System.out.println("Department: " + teacher.getDepartment());
            }
            System.out.println("----------------------------------------");
        }
    }

    private static void viewAllLockers() {
        System.out.println("\n=== All Lockers ===");
        
        for (Locker locker : lockers) {
            System.out.print("Locker #" + locker.getLockerId() + ": ");
            if (locker.isOccupied()) {
                System.out.println("Occupied by User ID: " + locker.getCurrentUserId());
            } else {
                System.out.println("Available");
            }
        }
    }

    private static void checkAllFines() {
        System.out.println("\n=== All User Fines ===");

        boolean finesFound = false;

        for (User user : users) {
            if (user instanceof Borrower) {
                Borrower borrower = (Borrower) user;
                double currentFine = calculateFine(borrower);
                double totalFine = currentFine;
                
                if (borrower instanceof Student) {
                    totalFine += ((Student) borrower).getTotalFines();
                } else if (borrower instanceof Teacher) {
                    totalFine += ((Teacher) borrower).getTotalFines();
                }

                if (totalFine > 0) {
                    finesFound = true;
                    System.out.println("User ID: " + borrower.getId());
                    if (borrower instanceof Student) {
                        System.out.println("User Type: Student");
                        System.out.println("Name: " + ((Student) borrower).getName());
                    } else if (borrower instanceof Teacher) {
                        System.out.println("User Type: Teacher");
                        System.out.println("Name: " + ((Teacher) borrower).getName());
                    }
                    System.out.println("Total Fine: " + totalFine + " TK");
                    System.out.println("----------------------------------------");
                }
            }
        }

        if (!finesFound) {
            System.out.println("No fines found for any users.");
        }
    }

    private static void addUser() {
        System.out.println("\n=== Add User ===");
        System.out.println("1. Add Admin");
        System.out.println("2. Add Student");
        System.out.println("3. Add Teacher");
        System.out.print("Choose user type: ");

        int choice = getIntInput();

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        // Check ID already exists
        for (User user : users) {
            if (user.getId().equals(id)) {
                System.out.println("A user with this ID already exists!");
                return;
            }
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        switch (choice) {
            case 1:
            		users.add(new Admin(id, password));
            		System.out.println("Admin added successfully!");
                break;
            case 2:
                System.out.print("Enter Name: ");
                String studentName = scanner.nextLine();
                System.out.print("Enter Department: ");
                String studentDept = scanner.nextLine();
                users.add(new Student(id, password, studentName, studentDept));
                System.out.println("Student added successfully!");
                break;
            case 3:
                System.out.print("Enter Name: ");
                String teacherName = scanner.nextLine();
                System.out.print("Enter Department: ");
                String teacherDept = scanner.nextLine();
                users.add(new Teacher(id, password, teacherName, teacherDept));
                System.out.println("Teacher added successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void searchBook() {
        System.out.println("\n=== Search Book ===");
        System.out.println("1. Search by ISBN");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Category");
        System.out.print("Choose search option: ");

        int choice = getIntInput();
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().toLowerCase();

        List<Book> results = new ArrayList<>();

        for (Book book : bookInventory) {
            switch (choice) {
                case 1:
                    if (book.getIsbn().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 2:
                    if (book.getTitle().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 3:
                    if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 4:
                    if (book.getCategory().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching your search.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book);
                System.out.println("----------------------------------------");
            }
        }
    }

    private static void borrowBook(Borrower borrower) {
        System.out.println("\n=== Borrow Book ===");

        // Check  reached maximum book limit
        if (borrower.getBorrowedBooks().size() >= MAX_BOOKS_PER_PERSON) {
            System.out.println("You have already borrowed the maximum number of books (" + MAX_BOOKS_PER_PERSON + ")!");
            return;
        }

        System.out.print("Enter ISBN of book to borrow: ");
        String isbn = scanner.nextLine();

        Book bookToBorrow = null;
        for (Book book : bookInventory) {
            if (book.getIsbn().equals(isbn)) {
                bookToBorrow = book;
                break;
            }
        }

        if (bookToBorrow == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return;
        }

        if (bookToBorrow.getAvailableCopies() <= 0) {
            System.out.println("No copies of this book are currently available!");
            return;
        }

        // Check if user already has a copy of this book
        for (BorrowedBook borrowedBook : borrower.getBorrowedBooks()) {
            if (borrowedBook.getIsbn().equals(isbn)) {
                System.out.println("You already have a copy of this book!");
                return;
            }
        }

        int borrowDays = (borrower instanceof Student) ? STUDENT_BORROW_DAYS : TEACHER_BORROW_DAYS;
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(borrowDays);

        BorrowedBook newBorrow = new BorrowedBook(isbn, bookToBorrow.getTitle(), borrowDate, dueDate);
        borrower.getBorrowedBooks().add(newBorrow);

        bookToBorrow.setAvailableCopies(bookToBorrow.getAvailableCopies() - 1);

        Transaction transaction = new Transaction(
            currentUser.getId(),
            TransactionType.BORROW,
            isbn,
            bookToBorrow.getTitle(),
            borrowDate
        );
        transactions.add(transaction);

        System.out.println("Book borrowed successfully!");
        System.out.println("Due date: " + dueDate);
    }

    private static void returnBook(Borrower borrower) {
        System.out.println("\n=== Return Book ===");

        if (borrower.getBorrowedBooks().isEmpty()) {
            System.out.println("You have no books to return!");
            return;
        }

        System.out.println("Your borrowed books:");
        int i = 1;
        for (BorrowedBook book : borrower.getBorrowedBooks()) {
            System.out.println(i + ". " + book.getTitle() + " (Due: " + book.getDueDate() + ")");
            i++;
        }

        System.out.print("Enter number of book to return: ");
        int bookIndex = getIntInput() - 1;

        if (bookIndex < 0 || bookIndex >= borrower.getBorrowedBooks().size()) {
            System.out.println("Invalid book number!");
            return;
        }

        BorrowedBook bookToReturn = borrower.getBorrowedBooks().get(bookIndex);

        // Check if the book is returned late
        LocalDate returnDate = LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(bookToReturn.getDueDate(), returnDate);
        double fine = 0.0;

        if (daysLate > 0) {
            fine = daysLate * LATE_RETURN_FINE_PER_DAY;
            System.out.println("You have returned the book late. Fine: " + fine + " TK");
        }

        // Update the book's available copies
        Book bookInInventory = null;
        for (Book book : bookInventory) {
            if (book.getIsbn().equals(bookToReturn.getIsbn())) {
                bookInInventory = book;
                break;
            }
        }

        if (bookInInventory != null) {
            bookInInventory.setAvailableCopies(bookInInventory.getAvailableCopies() + 1);
        }

        // Remove the book from the borrower's list
        borrower.getBorrowedBooks().remove(bookIndex);

        // Record the transaction
        Transaction transaction = new Transaction(
            currentUser.getId(),
            TransactionType.RETURN,
            bookToReturn.getIsbn(),
            bookToReturn.getTitle(),
            returnDate
        );
        transactions.add(transaction);

        // If there was a fine, add it to the user's total fines
        if (fine > 0) {
            borrower.addFine(fine);
        }

        System.out.println("Book returned successfully!");
        if (fine > 0) {
            System.out.println("Total fine to be paid: " + fine + " TK");
        }
    }

    private static void viewMyBooks(Borrower borrower) {
        System.out.println("\n=== My Books ===");

        if (borrower.getBorrowedBooks().isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }

        for (BorrowedBook book : borrower.getBorrowedBooks()) {
            System.out.println(book);
            System.out.println("----------------------------------------");
        }
    }

    private static void checkMyFines(Borrower borrower) {
        
    	double currentFine = calculateFine(borrower);
        double storedFine = 0.0;
        
        if (borrower instanceof Student) {
            storedFine = ((Student) borrower).getTotalFines();
        } else if (borrower instanceof Teacher) {
            storedFine = ((Teacher) borrower).getTotalFines();
        }
        
        System.out.println("Your previous unpaid fines: " + storedFine + " TK");
        System.out.println("Your current late return fines: " + currentFine + " TK");
        System.out.println("Your total fine: " + (storedFine + currentFine) + " TK");
    }

    private static void rentLocker(Borrower borrower) {
        System.out.println("\n=== Rent Locker ===");
        
        // Check user already have a locker
        for (Locker locker : lockers) {
            if (locker.isOccupied() && locker.getCurrentUserId().equals(currentUser.getId())) {
                System.out.println("You already have locker #" + locker.getLockerId());
                return;
            }
        }
        
        // Show available lockers
        System.out.println("Available lockers:");
        boolean anyAvailable = false;
        for (Locker locker : lockers) {
            if (!locker.isOccupied()) {
                System.out.println("Locker #" + locker.getLockerId());
                anyAvailable = true;
            }
        }
        
        if (!anyAvailable) {
            System.out.println("Sorry, no lockers are currently available.");
            return;
        }
        
        System.out.print("Enter locker number to rent: ");
        int lockerNumber = getIntInput();
        
        // Find and rent the locker
        for (Locker locker : lockers) {
            if (locker.getLockerId() == lockerNumber) {
                if (locker.isOccupied()) {
                    System.out.println("This locker is already occupied.");
                } else {
                    locker.occupy(currentUser.getId());
                    System.out.println("Locker #" + lockerNumber + " rented successfully.");
                }
                return;
            }
        }
        
        System.out.println("Invalid locker number.");
    }

    private static void releaseLocker(Borrower borrower) {
        System.out.println("\n=== Release Locker ===");
        
        // Find user's locker
       
        Locker userLocker = null;
        for (Locker locker : lockers) {
            if (locker.isOccupied() && locker.getCurrentUserId().equals(currentUser.getId())) {
                userLocker = locker;
                break;
            }
        }
        
        if (userLocker == null) {
            System.out.println("You don't have any locker rented.");
            return;
        }
        
        
        System.out.println("You have locker #" + userLocker.getLockerId() + " rented.");
        System.out.print("Do you want to release it? (Y/N): ");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("Y")) {
            userLocker.release();
            System.out.println("Locker released successfully.");
        } else {
            System.out.println("Locker release cancelled.");
        }
    }

    private static double calculateFine(Borrower borrower) {
        double totalFine = 0.0;
        for (BorrowedBook book : borrower.getBorrowedBooks()) {
            long daysLate = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
            if (daysLate > 0) {
                totalFine += daysLate * LATE_RETURN_FINE_PER_DAY;
            }
        }
                return totalFine;
    }

    private static int getIntInput() {
        
    	while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    //  classes for User, Book, Transaction, and Locker
    static abstract class User {
      
    	private String id;
        private String password;

        public User(String id, String password) {
            this.id = id;
            this.password = password;
        }

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }
    }

    static class Admin extends User {
        public Admin(String id, String password) {
            super(id, password);
        }
    }

    static abstract class Borrower extends User {
       
    	private List<BorrowedBook> borrowedBooks = new ArrayList<>();
        private double totalFines;

        public Borrower(String id, String password) {
            super(id, password);
        }

        public List<BorrowedBook> getBorrowedBooks() {
            return borrowedBooks;
        }

        public double getTotalFines() {
            return totalFines;
        }

        public void addFine(double fine) {
            totalFines += fine;
        }
    }

    static class Student extends Borrower {
        private String name;
        private String department;

        public Student(String id, String password, String name, String department) {
            super(id, password);
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
    }

    static class Teacher extends Borrower {
        private String name;
        private String department;

        public Teacher(String id, String password, String name, String department) {
           
        	super(id, password);
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
    }

    static class Book {
        
    	private String isbn;
        private String title;
        private String author;
        private String category;
        private int publicationYear;
        private int availableCopies;

        public Book(String isbn, String title, String author, String category, int publicationYear, int availableCopies) {
           
        	this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.category = category;
            this.publicationYear = publicationYear;
            this.availableCopies = availableCopies;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getCategory() {
            return category;
        }

        public int getPublicationYear() {
            return publicationYear;
        }

        public int getAvailableCopies() {
            return availableCopies;
        }

        public void setAvailableCopies(int availableCopies) {
            this.availableCopies = availableCopies;
        }

        @Override
        public String toString() {
            return "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nCategory: " + category +
                    "\nPublication Year: " + publicationYear + "\nAvailable Copies: " + availableCopies;
        }
    }

    static class BorrowedBook {
        private String isbn;
        private String title;
        private LocalDate borrowDate;
        private LocalDate dueDate;

        public BorrowedBook(String isbn, String title, LocalDate borrowDate, LocalDate dueDate) {
            this.isbn = isbn;
            this.title = title;
            this.borrowDate = borrowDate;
            this.dueDate = dueDate;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getBorrowDate() {
            return borrowDate;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        @Override
        public String toString() {
            return "Title: " + title + "\nBorrow Date: " + borrowDate + "\nDue Date: " + dueDate;
        }
    }

    static class Transaction {
       
    	private String userId;
        private TransactionType type;
        private String isbn;
        private String title;
        private LocalDate date;

        public Transaction(String userId, TransactionType type, String isbn, String title, LocalDate date) {
            this.userId = userId;
            this.type = type;
            this.isbn = isbn;
            this.title = title;
            this.date = date;
        }

        @Override
        public String toString() {
            return "User ID: " + userId + "\nTransaction Type: " + type + "\nISBN: " + isbn + "\nTitle: " + title + "\nDate: " + date;
        }
    }

    enum TransactionType {
        BORROW, RETURN
    }

    static class Locker {
        private int lockerId;
        private String currentUserId;

        public Locker(int lockerId) {
            this.lockerId = lockerId;
        }

        public int getLockerId() {
            return lockerId;
        }
        

        public boolean isOccupied() {
            return currentUserId != null;
        }

        public String getCurrentUserId() {
            return currentUserId;
        }

        public void occupy(String userId) {
            this.currentUserId = userId;
        }

        public void release() {
            this.currentUserId = null;
        }
    }
}