import java.io.*;
import java.util.*;
class Book 
{
    public String book_name;
    public String serial_no;
    public String author_name;
    public String publisher;
    public String edition;
    public int quantity;
    public List<String> issued_to_students;
    public Book(boolean loadFromFile) {
    // Initialize issued_to_students list
    issued_to_students = new ArrayList<>();
    
    if (!loadFromFile) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the book name: ");
        book_name = scanner.nextLine();
        System.out.print("Enter the serial number: ");
        serial_no = scanner.nextLine();
        System.out.print("Enter the Author name: ");
        author_name = scanner.nextLine();
        System.out.print("Enter Publisher name: ");
        publisher = scanner.nextLine();
        System.out.print("Enter the edition: ");
        edition = scanner.nextLine();
        System.out.print("Enter the quantity of books: ");
        quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    }
}
    public void initializeFromData(String data) {
        String[] parts = data.split("\\|");
        book_name = parts[1];
        serial_no = parts[2];
        author_name = parts[3];
        publisher = parts[4];
        edition = parts[5];
        quantity = Integer.parseInt(parts[6]);
    }

    public void initializeIssuedToStudents(String data) {
        String studentID = data.trim();//TO REMOVE WHITESPACES
        issued_to_students.add(studentID);
    }
}

class Student {
    public String student_name;
    public String uid;
    public String branch;
    public long mob_no;
    public List<String> issued_books;

    public Student(boolean loadFromFile) {
        // Initialize issued_books list
        issued_books = new ArrayList<>();

        if (!loadFromFile) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter student name: ");
            student_name = scanner.nextLine();
            System.out.print("Enter student ID: ");
            uid = scanner.nextLine();
            System.out.print("Enter student branch: ");
            branch = scanner.nextLine();
            System.out.print("Enter student mobile number: ");
            mob_no = scanner.nextLong();
            scanner.nextLine(); // Consume newline
        }
    }

    public void initializeFromData(String data) {
        String[] parts = data.split("\\|");
        student_name = parts[1];
        uid = parts[2];
        branch = parts[3];
        mob_no = Long.parseLong(parts[4]);
    }

    public void initializeIssuedBooks(String data) {
        String[] bookSerials = data.split("\\|");
        for (String bookSerial : bookSerials) {
            issued_books.add(bookSerial);
        }
    }
}

public class library {
    private List<Book> books;
    private List<Student> students;

    public library() {
        loadDataFromFile();
    }
    public void updateBookDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the serial number of the book you want to update: ");
        String serialNumber = scanner.nextLine();
        for (Book book : books) {
            if (book.serial_no.equals(serialNumber)) {
                System.out.println("Select the detail you want to update:");
                System.out.println("1. Book Name");
                System.out.println("2. Author Name");
                System.out.println("3. Publisher");
                System.out.println("4. Edition");
                System.out.println("5. Quantity");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        System.out.print("Enter new Book Name: ");
                        book.book_name = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter new Author Name: ");
                        book.author_name = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter new Publisher: ");
                        book.publisher = scanner.nextLine();
                        break;
                    case 4:
                        System.out.print("Enter new Edition: ");
                        book.edition = scanner.nextLine();
                        break;
                    case 5:
                        System.out.print("Enter new Quantity: ");
                        book.quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                System.out.println("Book details updated successfully.");
                return;
            }
        }
        System.out.println("Book with serial number " + serialNumber + " not found.");
    }

    public void updateStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the student you want to update: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.uid.equals(studentID)) {
                System.out.println("Select the detail you want to update:");
                System.out.println("1. Student Name");
                System.out.println("2. Branch");
                System.out.println("3. Mobile Number");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        System.out.print("Enter new Student Name: ");
                        student.student_name = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter new Branch: ");
                        student.branch = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter new Mobile Number: ");
                        student.mob_no = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                System.out.println("Student details updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        Book newBook = new Book(false);

        for (Book existingBook : books) {
            if (existingBook.serial_no.equals(newBook.serial_no)) {
                System.out.println("Book with serial number " + newBook.serial_no + " already exists.");
                return;
            }
        }

        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    public void searchBook(String identifier) {
        boolean found = false;
        for (Book book : books) {
            if (book.book_name.equalsIgnoreCase(identifier) || book.serial_no.equals(identifier)) {
                System.out.println("Book Name: " + book.book_name);
                System.out.println("Serial Number: " + book.serial_no);
                System.out.println("Author: " + book.author_name);
                System.out.println("Publisher: " + book.publisher);
                System.out.println("Edition: " + book.edition);
                System.out.println("Quantity Available: " + book.quantity);

                if (!book.issued_to_students.isEmpty()) {
                    System.out.println("Students who have issued this book:");
                    for (String studentID : book.issued_to_students) {
                        for (Student student : students) {
                            if (student.uid.equals(studentID)) {
                                System.out.println("Student Name: " + student.student_name);
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("No students have issued this book.");
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }
    public void deleteBook(String serialNumber) {
        boolean bookFound = false;

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.serial_no.equals(serialNumber)) {
                bookFound = true;

                // Check if the book is issued to any students
                if (!book.issued_to_students.isEmpty()) {
                    System.out.println("The book cannot be deleted because it is issued to students.");
                    return;
                }

                iterator.remove();
                System.out.println("Book with serial number " + serialNumber + " has been deleted.");
                return;
            }
        }

        if (!bookFound) {
            System.out.println("Book with serial number " + serialNumber + " not found.");
        }
    }
    // Add the rest of the methods here

    // Load data from file
    private void loadDataFromFile() {
    books = new ArrayList<>();
    students = new ArrayList<>();
    try {
        BufferedReader reader = new BufferedReader(new FileReader("librar_data.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            String type = parts[0];
            if (type.equals("Book")) {
                Book book = new Book(true);
                book.initializeFromData(line);
                books.add(book);
            } else if (type.equals("Student")) {
                Student student = new Student(true);
                student.initializeFromData(line);
                students.add(student);
            } else if (type.equals("IssuedByStudent")) {
                String studentID = parts[1];
                String[] bookSerials = parts[2].split(",");
                for (Book book : books) {
                    if (Arrays.asList(bookSerials).contains(book.serial_no)) {
                        book.issued_to_students.add(studentID);
                    }
                }
                for (Student student : students) {
                    if (student.uid.equals(studentID)) {
                        Collections.addAll(student.issued_books, bookSerials);
                    }
                }
            }
        }
        reader.close();
        System.out.println("Data loaded from file.");
    } catch (IOException e) {
        System.out.println("Failed to open the file for loading data.");
    }
}

    // Save data to file
    public void saveDataToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("librar_data.txt"));
            for (Book book : books) {
                writer.write("Book|" + book.book_name + "|" + book.serial_no + "|" + book.author_name + "|" + book.publisher + "|" + book.edition + "|" + book.quantity + "\n");
            }
            for (Student student : students) {
                writer.write("Student|" + student.student_name + "|" + student.uid + "|" + student.branch + "|" + student.mob_no + "\n");
                for (String bookSerial : student.issued_books) {
                    writer.write("IssuedByStudent|" + student.uid + "|" + bookSerial + "\n");
                }
            }
            writer.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to open the file for saving data.");
        }
    }

    public void issueBook(String studentID, String serialNumber) {
        boolean studentFound = false;
        boolean bookFound = false;
        
        for (Student student : students) {
            if (student.uid.equals(studentID)) {
                studentFound = true;
                for (Book book : books) {
                    if (book.serial_no.equals(serialNumber)) {
                        bookFound = true;
                        if (book.quantity > 0) {
                            System.out.println("Book " + book.book_name + " issued to student " + student.student_name);
                            book.quantity--;
                            student.issued_books.add(serialNumber);
                            book.issued_to_students.add(studentID);
                            return;
                        } else {
                            System.out.println("Book " + book.book_name + " is not available.");
                            return;
                        }
                    }
                }
                if (!bookFound) {
                    System.out.println("Book with serial number " + serialNumber + " not found.");
                }
            }
        }
        if (!studentFound) {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    public void returnBook(String studentID, String serialNumber) {
        boolean studentFound = false;
        boolean bookFound = false;
        
        for (Student student : students) {
            if (student.uid.equals(studentID)) {
                studentFound = true;
                for (Book book : books) {
                    if (book.serial_no.equals(serialNumber)) {
                        bookFound = true;
                        System.out.println("Book " + book.book_name + " returned by student " + student.student_name);
                        book.quantity++;
                        student.issued_books.remove(serialNumber);
                        book.issued_to_students.remove(studentID);
                        return;
                    }
                }
                if (!bookFound) {
                    System.out.println("Book with serial number " + serialNumber + " not found.");
                }
            }
        }
        if (!studentFound) {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    public void registerStudent() {
        Student newStudent = new Student(false);

        for (Student existingStudent : students) {
            if (existingStudent.uid.equals(newStudent.uid)) {
                System.out.println("Student with ID " + newStudent.uid + " already exists.");
                return;
            }
        }

        students.add(newStudent);
        System.out.println("Student registered successfully!");
    }

    public void unregisterStudent(String studentID) {
        boolean found = false;

        for (Student student : students) {
            if (student.uid.equals(studentID)) {
                students.remove(student);
                found = true;
                System.out.println("Student with ID " + studentID + " unregistered successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }
    public void searchStudent(String studentID) {
        for (Student student : students) {
            if (student.uid.equals(studentID)) {
                System.out.println("Student Name: " + student.student_name);
                System.out.println("Student ID: " + student.uid);
                System.out.println("Branch: " + student.branch);
                System.out.println("Mobile Number: " + student.mob_no);
                System.out.println("Books Issued:");
                if (!student.issued_books.isEmpty()) {
                    for (String bookSerial : student.issued_books) {
                        for (Book book : books) {
                            if (book.serial_no.equals(bookSerial)) {
                                System.out.println("NAME - " + book.book_name);
                            }
                        }
                    }
                } else {
                    System.out.println(" - No books issued.");
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }
    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students are registered.");
        } else {
            System.out.println("List of all registered students:");
            for (Student student : students) {
                System.out.println("Student Name: " + student.student_name);
                System.out.println("Student ID: " + student.uid);
                System.out.println("Branch: " + student.branch);
                System.out.println("Mobile Number: " + student.mob_no);
                System.out.println("-----------------------------");
            }
        }
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("List of all books in the library:");
            for (Book book : books) {
                System.out.println("Book Name: " + book.book_name);
                System.out.println("Serial Number: " + book.serial_no);
                System.out.println("Author: " + book.author_name);
                System.out.println("Publisher: " + book.publisher);
                System.out.println("Edition: " + book.edition);
                System.out.println("Quantity: " + book.quantity);
                System.out.println("-----------------------------");
            }
        }
    }
    public static void main(String[] args) {
    System.out.println("WELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
    System.out.println("=============================================");
    System.out.println("CREATORS:");
    System.out.println("Raj Mohnani: 22BCS15430");
    System.out.println("==============================================");
    System.out.println("Vanshika: 22BCS15478");
    System.out.println("==============================================");

    library library = new library();

    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("MENU");
        System.out.println("1. ADD NEW BOOK");
        System.out.println("2. REGISTER STUDENT");
        System.out.println("3. ISSUE A BOOK");
        System.out.println("4. RETURN A BOOK");
        System.out.println("5. SEARCH A BOOK");
        System.out.println("6. SHOW ALL BOOKS");
        System.out.println("7. UNREGISTER STUDENT");
        System.out.println("8. SEARCH A STUDENT");
        System.out.println("9. SHOW ALL REGISTERED STUDENTS");
        System.out.println("10. DELETE A BOOK");
        System.out.println("11. UPDATE BOOK DETAILS");
        System.out.println("12. UPDATE STUDENT DETAILS");
        System.out.println("13. EXIT APPLICATION");
        System.out.println("==============================================");
        System.out.print("ENTER YOUR CHOICE: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                library.addBook();
                break;
            case 2:
                library.registerStudent();
                break;
            case 3:
                System.out.print("Enter student ID: ");
                String studentID = scanner.nextLine();
                System.out.print("Enter book serial number: ");
                String serialNumber = scanner.nextLine();
                library.issueBook(studentID, serialNumber);
                break;
            case 4:
                System.out.print("Enter student ID: ");
                studentID = scanner.nextLine();
                System.out.print("Enter book serial number: ");
                serialNumber = scanner.nextLine();
                library.returnBook(studentID, serialNumber);
                break;
            case 5:
                System.out.print("Enter the book name or serial number to search: ");
                String identifier = scanner.nextLine();
                library.searchBook(identifier);
                break;
            case 6:
                library.showAllBooks();
                break;
            case 7:
                System.out.print("Enter the student ID to unregister: ");
                studentID = scanner.nextLine();
                library.unregisterStudent(studentID);
                break;
            case 8:
                System.out.print("Enter the student ID to search: ");
                studentID = scanner.nextLine();
                library.searchStudent(studentID);
                break;
            case 9:
                library.showAllStudents();
                break;
            case 10:
                System.out.print("Enter the serial number of the book to delete: ");
                serialNumber = scanner.nextLine();
                library.deleteBook(serialNumber);
                break;
            case 11:
                library.updateBookDetails();
                break;
            case 12:
                library.updateStudentDetails();
                break;
            case 13:
                library.saveDataToFile();
                System.out.println("EXIT SUCCESSFUL");
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}
}
