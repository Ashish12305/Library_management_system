
package library_management_system;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
	private static library_management_system library = new library_management_system();
	private static boolean isLoggedIn = false;
	private static String currentUser = null;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Library Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 1));

		 // Declare buttons
		 JButton studentLoginButton = new JButton("Student Login");
	     JButton facultyLoginButton = new JButton("Faculty Login");
	     JButton logoutButton = new JButton("Logout");
	     JButton addBookButton = new JButton("Add Book");
	     JButton issueBookButton = new JButton("Issue Book");
	     JButton returnBookButton = new JButton("Return Book");
	     JButton addStudentButton = new JButton("Add Student");
	     JButton listStudentsButton = new JButton("Students List");
	     JButton booksListButton = new JButton("Books List");
	     JButton exitButton = new JButton("Exit");


        panel.add(studentLoginButton);
        panel.add(facultyLoginButton);
        panel.add(logoutButton);
        panel.add(addBookButton);
        panel.add(issueBookButton);
        panel.add(returnBookButton);
        panel.add(addStudentButton);
        panel.add(listStudentsButton);
        panel.add(exitButton);


		frame.add(panel);
		frame.setVisible(true);
		
		
		studentLoginButton.addActionListener(e -> {
			String[] options = { "Register", "Login" };
			int choice = JOptionPane.showOptionDialog(frame, "Choose an option:","Student Login/Registration", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (choice == 0) { // Register
				String studentId = JOptionPane.showInputDialog("Enter Student ID:");
				String studentPassword = JOptionPane.showInputDialog("Enter Password:");
				if (studentId == null || studentId.trim().isEmpty() || studentPassword == null|| studentPassword.trim().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please provide valid details.");
					} else {
						library.registerStudent(studentId, studentPassword);
						JOptionPane.showMessageDialog(frame, "Student registered successfully!");
						}
				} else if (choice == 1) { // Login
					String studentId = JOptionPane.showInputDialog("Enter Student ID:");
					String studentPassword = JOptionPane.showInputDialog("Enter Password:");
					if (library.loginStudent(studentId, studentPassword)) {
						JOptionPane.showMessageDialog(frame, "Login successful!");
						} else {
							JOptionPane.showMessageDialog(frame, "Invalid credentials.");
							}
					}
			});
		
		facultyLoginButton.addActionListener(e -> {
			String[] options = { "Register", "Login" };
			int choice = JOptionPane.showOptionDialog(frame, "Choose an option:",
					"Faculty Login/Registration", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (choice == 0) { // Register
				String facultyId = JOptionPane.showInputDialog("Enter Faculty ID:");
				String facultyPassword = JOptionPane.showInputDialog("Enter Password:");
				if (facultyId == null || facultyId.trim().isEmpty() || facultyPassword == null|| facultyPassword.trim().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please provide valid details.");
					} else {
						library.registerFaculty(facultyId, facultyPassword);
						JOptionPane.showMessageDialog(frame, "Faculty registered successfully!");
						}
				} else if (choice == 1) { // Login
					String facultyId = JOptionPane.showInputDialog("Enter Faculty ID:");
					String facultyPassword = JOptionPane.showInputDialog("Enter Password:");
					if (library.loginFaculty(facultyId, facultyPassword)) {
						JOptionPane.showMessageDialog(frame, "Login successful!");
						} else {
							JOptionPane.showMessageDialog(frame, "Invalid credentials.");
							}
					}
			});
		
		logoutButton.addActionListener(e -> {
            if (!isLoggedIn) {
                JOptionPane.showMessageDialog(frame, "No user is logged in.");
                return;
            }
            isLoggedIn = false;
            currentUser = null;
            JOptionPane.showMessageDialog(frame, "Logout successful!");
        });
		
		
		addBookButton.addActionListener(e -> {
			if (!isLoggedIn) {
				JOptionPane.showMessageDialog(frame, "Please log in as a faculty member to add books.");
				return;
			}
			String bookId = JOptionPane.showInputDialog("Enter Book ID:");
			String bookTitle = JOptionPane.showInputDialog("Enter Book Title:");
			String bookAuthor = JOptionPane.showInputDialog("Enter Book Author:");
			if (bookId == null || bookId.trim().isEmpty() || bookTitle == null || bookTitle.trim().isEmpty() || bookAuthor == null || bookAuthor.trim().isEmpty()) 
					 {
				JOptionPane.showMessageDialog(frame, "You didn't provide complete book detail. Please try again.");
				return;
				}
			library.addBook(new Book(bookId, bookTitle, bookAuthor));
			JOptionPane.showMessageDialog(frame, "Book added successfully!");
			});
		
		addStudentButton.addActionListener(e ->{
			String studentId = JOptionPane.showInputDialog("Enter Student ID:");
			String studentName = JOptionPane.showInputDialog("Enter Student Name:");
			String studentDepartment = JOptionPane.showInputDialog("Enter Student Department:");
			library.addStudent(new student(studentId, studentName, studentDepartment));
			JOptionPane.showMessageDialog(frame, "Student added successfully!");
			});
		
		listStudentsButton.addActionListener(e -> {
			List<student> students = library.getStudents();
			String[] columnNames = { "Student ID", "Name", "Department", "Issued Book ID", "Book Title", "Author",
					"Issue Time" };
			String[][] data = new String[students.size()][7];
			for (int i = 0; i < students.size(); i++) {
				student s = students.get(i);
				data[i][0] = s.getId();
				data[i][1] = s.getName();
				data[i][2] = s.getDepartment();
				if (s.getIssuedBook() != null) {
					Book book = s.getIssuedBook();
					data[i][3] = book.getId();
					data[i][4] = book.getTitle();
					data[i][5] = book.getAuthor();
					data[i][6] = book.getIssueTime();
					
				} else {
					data[i][3] = "None";
					data[i][4] = "None";
					data[i][5] = "None";
					data[i][6] = "None";
				}
			}

			JTable table = new JTable(data, columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			JFrame tableFrame = new JFrame("Student Details");
			tableFrame.setSize(800, 400);
			tableFrame.add(scrollPane);
			tableFrame.setVisible(true);
		});

		// Issue Book Action
		issueBookButton.addActionListener(e -> {
			if (!isLoggedIn) {
				JOptionPane.showMessageDialog(frame, "Please log in as a faculty member to issue books.");
				return;
			}
			String bookId = JOptionPane.showInputDialog("Enter Book ID to issue:");
			String studentId = JOptionPane.showInputDialog("Enter Student ID:");
			boolean isIssued = library.issueBook(bookId, studentId);
			
			if(isIssued) {
				JOptionPane.showMessageDialog(frame, "The book with ID\"" + bookId + "\" is issued to student \"" + studentId + "\" .");
			} else {
				JOptionPane.showMessageDialog(frame, "Failed to issue the book. Please check the Book ID and Student ID.");
			}
			
		});
		

		// Return Book Action
		returnBookButton.addActionListener(e -> {
			if (!isLoggedIn) {
				JOptionPane.showMessageDialog(frame, "Please log in as a faculty member to return books.");
				return;
			}
		    String bookId = JOptionPane.showInputDialog("Enter Book ID to return:");
		    String studentId = JOptionPane.showInputDialog("Enter Student ID:");
		    List<student> students = library.getStudents();

		    for (student s : students) {
		        if (s.getId().equals(studentId) && s.getIssuedBook() != null && s.getIssuedBook().getId().equals(bookId)) {
		            library.returnBook(bookId);
		            s.setIssuedBook(null); // Clear the issued book from the student
		            JOptionPane.showMessageDialog(frame, " The Book with ID \"" + bookId + "\" has been returned by student \"" + studentId + "\".");
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(frame, "Invalid Book ID or Student ID, or the book is not issued.");
		});
		
		// Books List Action
		booksListButton.addActionListener(e -> {
		    List<Book> books = library.getBooks();
		    String[] columnNames = {"Book ID", "Title", "Author", "Availability", "Return Time", "Returned By"};
		    String[][] data = new String[books.size()][6];

		    for (int i = 0; i < books.size(); i++) {
		        Book book = books.get(i);
		        data[i][0] = book.getId();
		        data[i][1] = book.getTitle();
		        data[i][2] = book.getAuthor();
		        data[i][3] = book.isAvailable() ? "Available" : "Issued";
		        data[i][4] = book.getReturnTime() != null ? book.getReturnTime() : "N/A";
		        data[i][5] = book.getReturnedBy() != null ? book.getReturnedBy() : "N/A";
		    }

		    JTable table = new JTable(data, columnNames);
		    JScrollPane scrollPane = new JScrollPane(table);
		    JFrame tableFrame = new JFrame("Books List");
		    tableFrame.setSize(800, 400);
		    tableFrame.add(scrollPane);
		    tableFrame.setVisible(true);
		});

		// Exit Action
		exitButton.addActionListener(e -> System.exit(0));
	}
}
