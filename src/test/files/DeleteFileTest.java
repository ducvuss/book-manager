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

public class DeleteFileTest {
	
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
	public void removeLineFromEmptyFile() {
		assertFalse(fileController.delete(1));
	}	
	
	@Test
	public void removeLineNonEmptyFile() {
		fileController.create("0,test1,1,1");
		assertTrue(fileController.delete(1));
		assertEquals(0, fileController.read().size());
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
