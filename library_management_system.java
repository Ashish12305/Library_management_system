
package library_management_system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class library_management_system {
	private List<Book> books;
	private List<student> students;
	private HashMap<String, String> studentAccounts = new HashMap<>(); 
	private HashMap<String, String> facultyAccounts = new HashMap<>();
	
	public void registerStudent(String studentId, String password) {
	    studentAccounts.put(studentId, password);
	}
	
	public boolean loginStudent(String studentId, String password) {
	    return studentAccounts.containsKey(studentId) && studentAccounts.get(studentId).equals(password);
	}
	
	public void registerFaculty(String facultyId, String password) {
	    facultyAccounts.put(facultyId, password);
	}

	public boolean loginFaculty(String facultyId, String password) {
	    return facultyAccounts.containsKey(facultyId) && facultyAccounts.get(facultyId).equals(password);
	}// To store student credentials

	public library_management_system() {
		books = new ArrayList<>();
		students = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void addStudent(student student) {
		students.add(student);
	}

	public List<Book> getBooks() {
		return books;
	}

	public List<student> getStudents() {
		return students;
	}

	public boolean issueBook(String bookId, String studentId) {
		for (Book book : books) {
			if (book.getId().equals(bookId) && book.isAvailable()) {
				for (student s : students) {
					if (s.getId().equals(studentId)) {
						book.setAvailable(false);
						book.setIssueTime(
								LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
						s.setIssuedBook(book);
						return true; // Book issued successfully
					}
				}
			}
		}
		return false; // Failed to issue the book
	}

	public void returnBook(String bookId) {
		 for (Book book : books) {
	           if (book.getId().equals(bookId) && !book.isAvailable()) {
	               book.setAvailable(true);
	               String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	               book.setReturnTime(currentTime);
	               for (student s: students){
	            	   if (s.getIssuedBook()!=null && s.getIssuedBook().getId().equals(bookId)){
	            		   book.setReturnedBy(s.getName());
	            		   break;// Set the return time
	            	   }
	               }
	               System.out.println("Book returned successfully at " + currentTime);
	               return;
	           }
	       }
		 
	       System.out.println("Book is already available or does not exist.");
	   }
		
	}

