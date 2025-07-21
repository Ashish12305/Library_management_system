
package library_management_system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Book {
	private String id;
	private String title;
	private String author;
	private boolean isAvailable;
	private String issueTime;
	private String returnTime;
	private String returnedBy;

	public Book(String id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = true;
		this.issueTime = null;
		this.returnTime = null;
	}
	
	public String getReturnedBy() {
		return returnedBy;
	}
	
	public void setReturnedBy(String returnedBy) {
		this.returnedBy = returnedBy;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	@Override
	public String toString() {
		return "Book{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", author='" + author + '\''
				+ ", isAvailable=" + isAvailable + ", issueTime='" + issueTime + '\'' + ", returnTime='" + returnTime
				+ '\'' + '}';
	}
}
