import java.util.List;
import java.util.Scanner;

import controllers.AuthorController;
import controllers.BookController;
import controllers.FileController;
import controllers.PublisherController;
import models.Author;
import models.Book;

public class App {

	public static void main(String[] args) {

		String input = "start";
		AppRunner appRunner = new AppRunner();
		while (!input.equals("9")) {
			appRunner.printHeader("Welcome to my Book Management Main Menu:");
			System.out.println("- Press 1 for our Book menu.");
			System.out.println("- Press 2 for our Author menu.");
			System.out.println("- Press 3 for our Publisher menu.");
			System.out.println("- Press 9 to quit.");
			Scanner scanner = new Scanner(System.in);
			System.out.print("Your option: ");
			input = scanner.nextLine();
			appRunner.printLineBreak();
			switch (input) {
			case ("1"):
				appRunner.runBookMenu();
				break;
			case ("2"):
				appRunner.runAuthorMenu();
				break;
			case ("3"):
				appRunner.runPublisherMenu();
				break;
			}
		}
	}
}

class AppRunner {

	private BookController bookController;
	private AuthorController authorController;
	private PublisherController publisherController;

	public AppRunner() {
		this.bookController = new BookController("resources/books.csv");
		this.authorController = new AuthorController("resources/authors.csv");
		this.publisherController = new PublisherController("resources/publishers.csv");
	}

	public void runBookMenu() {

		String input = "start";
		while (!input.equals("9")) {
			printHeader("Welcome to book menu:");
			printOptions(new String[] { "read books", "create book", "update book", "delete book" });
			Scanner scanner = new Scanner(System.in);
			System.out.print("Your option: ");
			input = "start";
			input = scanner.nextLine();
			printLineBreak();
			switch (input) {
			case ("1"):
				List<Book> books = bookController.readBooks();
				if (books == null)
					break;
				books.forEach(x -> {
					System.out.printf("%s - %s - %s - %s\n", x.getBookId(), x.getBookName(),
							authorController.getAuthorById(x.getAuthorId()),
							publisherController.getNameById(x.getPublisherId()));
				});
				;
				break;
			case ("2"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the book title: ");
				scanner = new Scanner(System.in);
				String title = scanner.nextLine();
				System.out.print("Enter the author: ");
				scanner = new Scanner(System.in);
				String author = scanner.nextLine();
				String authorId = authorController.getAuthorIdByName(author);
				if (authorId == null) {
					authorId = authorController.appendAuthor(author).toString();
				}
				System.out.print("Enter the publisher: ");
				scanner = new Scanner(System.in);
				String publisher = scanner.nextLine();
				String publisherId = publisherController.getIdByName(publisher);
				if (publisherId == null) {
					publisherId = publisherController.appendPublisher(publisher).toString();
				}
				bookController.appendBook(new Book(title, authorId, publisherId));
			case ("3"):
				runUpdateBookMenu();
				break;
			}
			printLineBreak();
		}
	}

	private void runUpdateBookMenu() {
		Scanner scanner = new Scanner(System.in);
		printHeader("Complete the form below: ");
		System.out.print("Enter the book Id: ");
		scanner = new Scanner(System.in);
		String bookId = scanner.nextLine();
		if(bookController.getNameById(Integer.parseInt(bookId)) != null) {
			System.out.print("Enter the book title: ");
			scanner = new Scanner(System.in);
			String bookName = scanner.nextLine();
			System.out.print("Enter the author: ");
			scanner = new Scanner(System.in);
			String authorName = scanner.nextLine();
			String authorId = authorController.getAuthorIdByName(authorName);
			if (authorId == null) {
				authorId = authorController.appendAuthor(authorName).toString();
			}
			System.out.print("Enter the publisher: ");
			scanner = new Scanner(System.in);
			String publisher = scanner.nextLine();
			String publisherId = publisherController.getIdByName(publisher);
			if (publisherId == null) {
				publisherId = publisherController.appendPublisher(publisher).toString();
			}
		}
	}

	public void runAuthorMenu() {
		String input = "start";
		while (!input.equals("9")) {
			printHeader("Welcome to author menu:");
			printOptions(new String[] { "read authors", "create author", "update author", "delete author" });
			Scanner scanner = new Scanner(System.in);
			System.out.print("Your option: ");
			input = "start";
			input = scanner.nextLine();
			printLineBreak();
			switch (input) {
			case ("1"):
				printHeader("List of Authors: ");
				authorController.readAuthors();
				break;
			case ("2"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the author's name: ");
				scanner = new Scanner(System.in);
				String author = scanner.nextLine();
				if (authorController.getAuthorIdByName(author) != null) {
					System.out.println("Author existed");
					break;
				}
				if (authorController.appendAuthor(author) != null) {
					System.out.println("Successfully added");
				} else {
					System.out.println("Failed to add record, please try again");
				}
				break;
			case ("3"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the author's ID: ");
				scanner = new Scanner(System.in);
				String authorId = scanner.nextLine();
				System.out.print("Enter the author's name: ");
				scanner = new Scanner(System.in);
				String authorName = scanner.nextLine();
				if (authorController.updateAuthor(authorId, authorName)) {
					System.out.println("Successfully updated");
				} else {
					System.out.println("Failed to update author, please try again");
				}
				break;
			case ("4"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the author's ID: ");
				scanner = new Scanner(System.in);
				String rowId = scanner.nextLine();
				if (authorController.delete(Integer.parseInt(rowId))) {
					bookController.deleteBookByAuthorId(rowId);
					System.out.println("Successfully deleted");
				} else {
					System.out.println("Failed to delete author, please try again");
				}
				break;
			}
			printLineBreak();
		}
	}

	public void runPublisherMenu() {
		String input = "start";
		while (!input.equals("9")) {
			printHeader("Welcome to publisher menu:");
			printOptions(
					new String[] { "read publishers", "create publisher", "update publisher", "delete publisher" });
			Scanner scanner = new Scanner(System.in);
			System.out.print("Your option: ");
			input = "start";
			input = scanner.nextLine();
			printLineBreak();
			switch (input) {
			case ("1"):
				printHeader("List of publishers: ");
				publisherController.readPublishers();
				break;
			case ("2"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the author's name: ");
				scanner = new Scanner(System.in);
				String publisher = scanner.nextLine();
				if (publisherController.getIdByName(publisher) != null) {
					System.out.println("publisher existed");
					break;
				}
				if (publisherController.appendPublisher(publisher) != null) {
					System.out.println("Successfully added");
				} else {
					System.out.println("Failed to add record, please try again");
				}
				break;
			case ("3"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the publisher's ID: ");
				scanner = new Scanner(System.in);
				String publisherId = scanner.nextLine();
				System.out.print("Enter the publisher's name: ");
				scanner = new Scanner(System.in);
				String publisherName = scanner.nextLine();
				if (publisherController.updatePublisher(publisherId, publisherName)) {
					System.out.println("Successfully updated");
				} else {
					System.out.println("Failed to update publisher, please try again");
				}
				break;
			case ("4"):
				printHeader("Complete the form below: ");
				System.out.print("Enter the publisher's ID: ");
				scanner = new Scanner(System.in);
				String rowId = scanner.nextLine();
				if (publisherController.delete(Integer.parseInt(rowId))) {
					bookController.deleteBookByPublisherId(rowId);
					System.out.println("Successfully deleted");
				} else {
					System.out.println("Failed to delete publisher, please try again");
				}
				break;
			}
			printLineBreak();
		}
	}

	public void printLineBreak() {
		System.out.println("-------------------------------------------");
	}

	public void printHeader(String menuHeader) {
		printLineBreak();
		System.out.println(" " + menuHeader);
		printLineBreak();
	}

	public void printOptions(String[] options) {
		int count = 1;
		for (String option : options) {
			System.out.printf("- Press %d for %s.\n", count++, option);
		}
		System.out.println("- Press 9 to go back to the main menu.");
	}
}