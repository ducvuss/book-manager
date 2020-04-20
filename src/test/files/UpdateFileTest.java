package test.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.FileController;

public class UpdateFileTest {
	
	String filepath = "resources/booksTest.csv";
	FileController fileController;
	String replacedBook = "1,Updated Book,1,2";
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		fileController = new FileController(file.getPath());
	}
	
	@Test
	public void updateToEmptyFile() {
		assertFalse(fileController.update(1, replacedBook));
	}	

	@Test
	public void updateNonEmptyFile() {
		fileController.create("0,test1,1,1");
		assertTrue(fileController.update(1, replacedBook));
		assertEquals(replacedBook, fileController.read().get(0));
	}
	
	@Test
	public void updateMultipleRecord() {
		fileController.create("0,test1,1,1");
		fileController.create("0,test2,1,1");
		fileController.create("0,test3,1,1");
		fileController.create("0,test4,1,1");
		fileController.create("0,test5,1,1");
		fileController.create("0,test6,1,1");
		assertTrue(fileController.update(1, replacedBook));
		assertEquals("Updated Book", fileController.getNameById(1));
		assertTrue(fileController.update(3, "3,updated book 2,2,2"));
		assertEquals("updated book 2", fileController.getNameById(3));
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
