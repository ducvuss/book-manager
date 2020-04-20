package test.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.FileController;

public class ReadFileTest {
	
	String filepath = "resources/booksTest.csv";
	FileController fileController;
	
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		fileController = new FileController(file.getPath());
	}

	@Test
	public void readFromEmptyFile() {
		assertEquals(0, fileController.read().size());
	}
	
	@Test
	public void readNonEmptyFile() {
		fileController.create("1,test1,1,1");
		assertTrue(fileController.read().get(0).equals("1,test1,1,1"));
		assertEquals(1, fileController.read().size());
	}
	//AfterClass - after the class
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
