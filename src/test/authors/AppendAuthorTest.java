package test.authors;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.AuthorController;

public class AppendAuthorTest {
	
	String filepath = "resources/authorsTest.csv";
	AuthorController authorController;
	String author = "1,Duc Vu";
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		authorController = new AuthorController(file.getPath());
		
	}
	
	@Test
	public void appendToEmptyFile() {
		authorController.appendAuthor("Duc Vu");
		assertEquals(1, authorController.read().size());
		assertEquals(author, authorController.read().get(0));
	}
	@Test
	public void appendMultipleRecords() {
		authorController.appendAuthor("Duc Vu");
		authorController.appendAuthor("David Lee");
		authorController.appendAuthor("John Wayne");
		assertEquals("3", authorController.getAuthorIdByName("John Wayne"));
		assertEquals("2", authorController.getAuthorIdByName("David Lee"));
		assertEquals("1", authorController.getAuthorIdByName("Duc Vu"));
		assertEquals(3, authorController.read().size());
		assertEquals(author, authorController.read().get(0));
		assertEquals("2,David Lee", authorController.read().get(1));
		assertEquals("3,John Wayne", authorController.read().get(2));
		
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
