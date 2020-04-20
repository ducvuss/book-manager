package test.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.BookController;

public class ReadBookTest {
	
	String filepath = "resources/booksTest.csv";
	BookController bookController;
	
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		bookController = new BookController(file.getPath());
	}

	@Test
	public void readFromEmptyFile() {
		assertEquals(0, bookController.read().size());
	}
	
	@Test
	public void readNonEmptyFile() {
		bookController.create("1,test1,1,1");
		assertTrue(bookController.read().get(0).equals("1,test1,1,1"));
		assertEquals(1, bookController.read().size());
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
