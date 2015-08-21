package bdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
 

import gherkin.formatter.model.DataTableRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 

 



import cucumber.api.DataTable;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
	DataTable table =null;
 
	@Given(".+book with the title '(.+)', written by '(.+)', published in (.+)")
	public void addNewBook(final String title, final String author, @Format("dd MMMMM yyyy") final String published) {
		Book book = new Book(title, author, published);
		List<Book> store = library.addBook(book);
		table = table.create(store);
	}
	
	@Given("^following Books exist:$")
	public void following_Books_exist(DataTable books) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
//	    throw new PendingException();
		table = books;
		 library.addBooks(books);
			
	}
	
	@When("^customer searches for all the books$")
	public void customer_searches_for_all_the_books() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    result = library.findBooks();
	}
 
	@When("^the customer searches for books published between (\\d+) and (\\d+)$")
	public void setSearchParameters(@Format("yyyy") final Calendar from, @Format("yyyy") final Calendar to) {
		result = library.findBooks(from, to);
	}
	
	@When("the customer searches for books written by '(.+)'")
	public void setSearchByAuthor(final String author)
	{
		result = library.findBooks(author);
	}
	
	@When("^the customer searches for book '(.+)'")
	public void the_customer_searches_for_book_Some_other_book(final String bookTitle) throws Throwable {
	    // Express the Regexp above with the code you wish you had
		result = library.findBookName(bookTitle);
	}	
	
	@Then("^(\\d+) books should have found$")
	public void books_should_have_found(final int booksFound) throws Throwable {
	    // Express the Regexp above with the code you wish you had
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("(\\d+) books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
	
	@Then("^Book (\\d+) should have the Author '(.+)'$")
	public void Book_should_have_the_Author_Tim_Tomson(final int position,final String authorName) throws Throwable {
	    // Express the Regexp above with the code you wish you had		
	    assertThat(result.get(position - 1).getAuthor(),equalTo(authorName));
	}
	
	@Then("^following books should exist:$")
	public void following_books_should_exist(DataTable books) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    books.diff(table);
	}
}