package test.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.BookController;

public class UpdateBookTest {
	
	String filepath = "resources/booksTest.csv";
	BookController bookController;
	String replacedBook = "1,Updated Book,1,2";
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		bookController = new BookController(file.getPath());
	}
	
	@Test
	public void updateToEmptyFile() {
		assertFalse(bookController.update(1, replacedBook));
	}	
	
	@Test
	public void updateNonEmptyFile() {
		bookController.create("0,test1,1,1");
		assertTrue(bookController.update(1, replacedBook));
		assertEquals(replacedBook, bookController.read().get(0));
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
