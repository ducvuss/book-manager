package test.authors;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.AuthorController;

public class GetAuthorsTest {
	
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
	public void getAuthors() {
		assertEquals(0, authorController.getAuthors().size());
	}
	@Test
	public void GetMultipleIdsTest() {
		authorController.appendAuthor("Duc Vu");
		assertEquals(1, authorController.getAuthors().size());
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
