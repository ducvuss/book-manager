package test.authors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.AuthorController;

public class UpdateAuthorTest {
	
	String filepath = "resources/authorsTest.csv";
	AuthorController authorController;
	String replacedAuthor = "1,Updated Author,1,2";
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		authorController = new AuthorController(file.getPath());
	}
	
	@Test
	public void updateToEmptyFile() {
		assertFalse(authorController.update(1, replacedAuthor));
	}	
	
	@Test
	public void updateNonEmptyFile() {
		authorController.create("0,test1,1,1");
		authorController.create("0,test2,1,1");
		authorController.create("0,test3,1,1");
		assertTrue(authorController.update(1, replacedAuthor));
		assertEquals("Updated Author", authorController.getAuthorById(1));
		assertEquals(3, authorController.read().size());
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
