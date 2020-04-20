package test.authors;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.AuthorController;

public class GetAuthorIdByNameTest {
	
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
	public void getIdFromEmptyFile() {
		assertEquals(null, authorController.getAuthorIdByName("John Wayne"));
	}
	@Test
	public void GetMultipleIdsTest() {
		authorController.appendAuthor("Duc Vu");
		authorController.appendAuthor("David Lee");
		authorController.appendAuthor("John Wayne");
		assertEquals("3", authorController.getAuthorIdByName("John Wayne"));
		assertEquals("2", authorController.getAuthorIdByName("David Lee"));
		assertEquals("1", authorController.getAuthorIdByName("Duc Vu"));
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
