package controllers;

import java.util.List;
import java.util.stream.Collectors;

import models.Author;
import models.Book;

public class AuthorController extends FileController {
	public AuthorController(String filepath) {
		super(filepath);
	}
	
	public Integer appendAuthor(String authorName) {
		return create("0," + authorName);
	}
	
	public boolean updateAuthor(String authorId, String authorName) {
		return update(Integer.parseInt(authorId), String.format("%s,%s", authorId, authorName));
	}
	
	public List<Author> getAuthors() {
		return read().stream()
				.map(x -> new Author(x))
				.collect(Collectors.toList());
	}
	
	public List<Author> putAuthor(Integer authorId, Author author) {
		List<Author> listAuthors = getAuthors();
		for(Author authorRow : listAuthors) {
			if (authorRow.getAuthorId() == authorId) {
				listAuthors.remove(authorRow);
			}
		}
		
		listAuthors.add(new Author(authorId, author.getAuthorName()));
		
		return listAuthors;
	}

	public void readAuthors() {
		List<String> authors = read();
		if (authors.size() == 0) {
			System.out.println("No author found in the database");
		} else {
			authors.stream().forEach(x -> System.out.println(new Author(x).toString()));
		}
	}

	public String getAuthorById(Integer authorId) {
		return getNameById(authorId);
	}

	public String getAuthorIdByName(String name) {
		return getIdByName(name);
	}

}
