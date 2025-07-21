
package library_management_system;

public class student {
	private String id;
	private String name;
	private String department;
	private Book issuedBook;

	public student(String id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.issuedBook = null;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public Book getIssuedBook() {
		return issuedBook;
	}

	public void setIssuedBook(Book issuedBook) {
		this.issuedBook = issuedBook;
	}

	@Override
	public String toString() {
		return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", department='" + department + '\''
				+ ", issuedBook=" + (issuedBook != null ? issuedBook.toString() : "None") + '}';
	}
}
