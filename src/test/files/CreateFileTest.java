package test.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.FileController;

public class CreateFileTest {
	
	String filepath = "resources/booksTest.csv";
	FileController fileController;
	
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		fileController = new FileController(file.getPath());
		
	}

	@Test
	public void appendToEmptyFile() {
		assertTrue(fileController.create("0,test1,1,1") == 1);
		assertEquals(1, fileController.read().size());
		assertEquals("1,test1,1,1", fileController.read().get(0));
	}

	@Test
	public void appendToEmptyFileAfterDeleteFromFile() {
		assertTrue(fileController.create("0,test1,1,1") == 1);
		assertTrue(fileController.create("0,test2,1,1") == 2);
		assertTrue(fileController.create("0,test3,1,1") == 3);
		assertEquals(3, fileController.read().size());
		fileController.delete(1);
		assertTrue(fileController.create("0,test3,1,1") == 4);
		fileController.delete(4);
		assertTrue(fileController.create("0,test3,1,1") == 4);
		assertEquals("test3", fileController.getNameById(4));
	}

	@Test
	public void appendToEmptyFileAfterUpdateToFile() {
		assertTrue(fileController.create("0,test1,1,1") == 1);
		assertTrue(fileController.create("0,test2,1,1") == 2);
		assertTrue(fileController.create("0,test3,1,1") == 3);
		assertEquals(3, fileController.read().size());
		fileController.update(2, "0,test4,1,1");
		assertTrue(fileController.create("0,test4,1,1") == 4);
		fileController.update(1, "0,test5,1,1");
		assertTrue(fileController.create("0,test5,1,1") == 5);
		assertEquals("test4", fileController.getNameById(4));
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
