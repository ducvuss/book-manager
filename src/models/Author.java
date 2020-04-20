package models;

public class Author {
	private String authorName;
	private String authorId;

	public Author(String infoString) {
		String[] info = infoString.split(",");
		this.authorId = (info[0]);
		this.setAuthorName(info[1]);
	}

	public Author(Integer authorId, String authorName) {
		this.authorId = authorId.toString();
		this.authorName = authorName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return Integer.parseInt(authorId);
	}
	
	public String toString() {
		return String.format("%s - %s", authorId, authorName);
	}
}
