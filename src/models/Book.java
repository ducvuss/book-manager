package models;

public class Book {
	private String bookId;
	private String bookName;
	private String authorId;
	private String publisherId;

	public Book(String infoString) {
		String[] info = infoString.split(",");
		this.bookId = info[0];
		this.setBookName(info[1]);
		this.setAuthorId(info[2]);
		this.setPublisherId(info[3]);
	}	
	
	public Book(String bookName, String authorId, String publisherId) {
		this.bookId = "0";
		this.setBookName(bookName);
		this.setAuthorId(authorId);
		this.setPublisherId(publisherId);
	}

	public Integer getBookId() {
		return Integer.parseInt(this.bookId);
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getAuthorId() {
		return Integer.parseInt(authorId);
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Integer getPublisherId() {
		return Integer.parseInt(publisherId);
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	
	public String toString() {
		return String.format("%s,%s,%s,%s", bookId, bookName, authorId, publisherId);
	}
}
