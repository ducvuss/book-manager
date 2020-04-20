package test.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.BookController;

public class DeleteBookTest {
	
	String filepath = "resources/booksTest.csv";
	BookController bookController;
	String replacedBook = "1,Updated Book,1,2";
	@Before
	public void beforeTest() throws IOException {
		File file = new File(filepath);
		file.createNewFile();
		bookController = new BookController(file.getPath());
	}

	@Test
	public void deleteBookWithAuthorId() {
		assertTrue(bookController.create("0,test1,1,1") == 1);
		assertEquals(1, bookController.read().size());
		assertEquals("1,test1,1,1", bookController.read().get(0));
		bookController.deleteBookByAuthorId(3 + "");
		assertEquals(1, bookController.read().size());
		assertEquals("1,test1,1,1", bookController.read().get(0));
		assertTrue(bookController.create("0,test1,3,4") == 2);
		assertEquals(2, bookController.read().size());
		bookController.deleteBookByAuthorId(3 + "");
		assertEquals(1, bookController.read().size());
		
	}	
	@Test
	public void deleteBooksWithAuthorId() {
		assertTrue(bookController.create("0,test1,1,1") == 1);
		assertTrue(bookController.create("0,test2,3,4") == 2);
		assertTrue(bookController.create("0,test3,2,3") == 3);
		assertTrue(bookController.create("0,test4,3,4") == 4);
		assertTrue(bookController.create("0,test5,2,6") == 5);
		assertTrue(bookController.create("0,test6,3,4") == 6);
		assertEquals(6, bookController.read().size());
		bookController.deleteBookByAuthorId(3 + "");
		List<String> strings = bookController.read();
		assertEquals(3, strings.size());
		assertFalse(strings.contains("2,test2,3,4"));
		assertFalse(strings.contains("4,test4,3,4"));
		assertFalse(strings.contains("6,test6,3,4"));
		assertTrue(strings.contains("1,test1,1,1"));
		assertTrue(strings.contains("3,test3,2,3"));
		assertTrue(strings.contains("5,test5,2,6"));
		
	}	
	
	@Test
	public void deleteBooksWithPublisherId() {
		assertTrue(bookController.create("0,test1,1,1") == 1);
		assertTrue(bookController.create("0,test2,3,4") == 2);
		assertTrue(bookController.create("0,test3,2,3") == 3);
		assertTrue(bookController.create("0,test4,3,4") == 4);
		assertTrue(bookController.create("0,test5,2,3") == 5);
		assertTrue(bookController.create("0,test6,3,4") == 6);
		assertEquals(6, bookController.read().size());
		bookController.deleteBookByPublisherId(3 + "");
		List<String> strings = bookController.read();
		assertEquals(4, strings.size());
		assertFalse(strings.contains("3,test3,2,3"));
		assertFalse(strings.contains("5,test5,2,3"));
		assertTrue(strings.contains("1,test1,1,1"));
		assertTrue(strings.contains("2,test2,3,4"));
		assertTrue(strings.contains("4,test4,3,4"));
		assertTrue(strings.contains("6,test6,3,4"));
		
	}	
	
	@Test
	public void deleteBookWithAuthorIdBasic() {
		assertTrue(bookController.create("0,test1,3,1") == 1);
		assertEquals(1, bookController.read().size());
		assertEquals("1,test1,3,1", bookController.read().get(0));
		bookController.deleteBookByAuthorId("3");
		assertEquals(0, bookController.read().size());
		
	}	
	
	@Test
	public void deleteBookWithPublisherId() {
		assertTrue(bookController.create("0,test1,1,4") == 1);
		assertEquals(1, bookController.read().size());
		assertEquals("1,test1,1,4", bookController.read().get(0));
		bookController.deleteBookByPublisherId(4 + "");
		assertEquals(0, bookController.read().size());
	}
	
	@After
	public void afterTest() {
		File file = new File(filepath);
		file.delete();
	}
	
}
