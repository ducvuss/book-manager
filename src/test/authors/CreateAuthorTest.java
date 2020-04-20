package test.authors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.AuthorController;

public class CreateAuthorTest {
	
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
		assertTrue(authorController.create("0,Duc Vu") == 1);
		assertEquals(1, authorController.read().size());
		assertEquals(author, authorController.read().get(0));
	}
	
	@Test
	public void appendMultipleRecords() {
		assertTrue(authorController.create("0,Duc Vu") == 1);
		assertTrue(authorController.create("0,David Lee") == 2);
		assertTrue(authorController.create("0,John Wayne") == 3);
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
