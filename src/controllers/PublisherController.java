package controllers;

import java.util.List;

import models.Publisher;
import models.Book;

public class PublisherController extends FileController {
	public PublisherController(String filepath) {
		super(filepath);
	}
	
	public Integer appendPublisher(String publisherName) {
		return create("0," + publisherName);
	}
	
	public void readPublishers() {
		List<String> publishers = read();
		if (publishers.size() == 0) {
			System.out.println("No publisher found in the database");
		} else {
			publishers.stream().forEach(x -> System.out.println(new Publisher(x).toString()));
		}
	}

	public boolean updatePublisher(String publisherId, String publisherName) {
		return update(Integer.parseInt(publisherId), String.format("%s,%s", publisherId, publisherName));
	}

}
