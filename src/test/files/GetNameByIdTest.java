package test.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.FileController;

public class GetNameByIdTest {
	String filepath = "resources/booksTest.csv";
	FileController fileController;
	
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		fileController = new FileController(file.getPath());
		
	}

	@Test
	public void getIdFromEmptyFile() {
		assertEquals(null, fileController.getNameById(1));
	}
	
	@Test
	public void getIdFromNonEmptyFile() {
		assertTrue(fileController.create("0,test1,1,1") == 1);
		assertTrue(fileController.create("0,test2,1,1") == 2);
		assertTrue(fileController.create("0,test3,1,1") == 3);
		assertEquals(3, fileController.read().size());
		assertEquals(null, fileController.getNameById(4));
		assertEquals("test1", fileController.getNameById(1));
		assertEquals("test2", fileController.getNameById(2));
		assertEquals("test3", fileController.getNameById(3));
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
}
