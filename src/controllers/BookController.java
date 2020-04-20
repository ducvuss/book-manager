package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import models.Book;

public class BookController extends FileController {
	public BookController(String filepath) {
		super(filepath);
	}
	
	public void appendBook(Book book) {
		if (create(book.toString()) == null) {
			System.out.println("Failed to create book, please try again");
		};
	}
	
	public void updateBook(Book book) {
		if (!update(book.getBookId(), book.toString())) {
			System.out.println("Failed to create book, please try again");
		};
	}

	public List<Book> readBooks() {
		List<String> books = read();
		if (books.size() == 0) {
			System.out.println("No book found in the database");
			return null;
		} else {
			return books.stream().map(x -> new Book(x)).collect(Collectors.toList());
		}
	}

	public boolean deleteBookByPublisherId(String rowId) {
		List<String> strings = read();
		if (strings.removeIf(row -> row.split(",")[3].equals(rowId))) {
			try {
				Files.write(filepath, strings);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean deleteBookByAuthorId(String rowId) {
		List<String> strings = read();
		if (strings.removeIf(row -> row.split(",")[2].equals(rowId))) {
			try {
				Files.write(filepath, strings);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
