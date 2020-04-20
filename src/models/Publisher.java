package models;

public class Publisher {
	private String publisherName;
	private String publisherId;

	public Publisher(String infoString) {
		String[] info = infoString.split(",");
		this.publisherId = (info[0]);
		this.setPublisherName(info[1]);
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Integer getPublisherId() {
		return Integer.parseInt(publisherId);
	}
	
	public String toString() {
		return String.format("%s - %s", publisherId, publisherName);
	}
}
