package bdd;

import gherkin.formatter.model.DataTableRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cucumber.api.DataTable;

public class Library {


	private final List<Book> store = new ArrayList<>();

	public List<Book> addBook(final Book book)
	{
		store.add(book);

		return store;
	}



	public void addBooks(final DataTable books)
	{

		Book book=null;
		int i=0;
		List<DataTableRow> listOfBooks  = books.getGherkinRows();
		DataTableRow row = null;
		List<String> column = null;
		while(listOfBooks.size() > i)
		{
			if(i!=0)
			{
				row  = listOfBooks.get(i);
				column = row.getCells();
				book = new Book(column.get(0),column.get(1),column.get(2));
				store.add(book);
			}
			i++;
		}
	}

	public List<Book> findBooks(final Calendar from,final Calendar to)
	{
		int publishedYear=0;
		Calendar pYear = null;
		String year = "";
		int i =0;
		List<Book> filteredStore = new ArrayList<Book>();
		Book book =null;
		int maxStoreSize = store.size();
		while(maxStoreSize>i)
		{					
			pYear = store.get(i).getPublished();
			if(pYear==null)
			{
				year = store.get(i).getDate();
				StringBuffer sb = new StringBuffer(year);
				sb = sb.reverse();	
				year = sb.substring(0, 4);
				sb = new StringBuffer(year);
				sb = sb.reverse();
				year = sb.toString();

				publishedYear =Integer.parseInt(year);
			}
			else
			{
				publishedYear = store.get(i).getPublished().getWeekYear();
			}
			if(publishedYear>=from.getWeekYear() && publishedYear<=to.getWeekYear())
			{
				book = store.get(i);
				filteredStore.add(book);
			}


			i++;
		}


		return filteredStore;

	}

	public List<Book> findBooks()
	{
		return store;
	}


	public List<Book> findBooks(final String author)
	{
		String  authorName="";
		int i =0;
		List<Book> filteredStore = new ArrayList<Book>();
		Book book =null;
		int maxStoreSize = store.size();
		while(maxStoreSize>i)
		{						

			authorName = store.get(i).getAuthor();
			if(authorName.equalsIgnoreCase(author))
			{
				book = store.get(i);
				filteredStore.add(book);
			}


			i++;
		}


		return filteredStore;

	}

	public List<Book> findBookName(final String bookName)
	{
		String  bookTitle="";
		int i =0;
		List<Book> filteredStore = new ArrayList<Book>();
		Book book =null;
		int maxStoreSize = store.size();
		while(maxStoreSize>i)
		{						

			bookTitle = store.get(i).getTitle();
			if(bookTitle.equalsIgnoreCase(bookName))
			{
				book = store.get(i);
				filteredStore.add(book);
			}


			i++;
		}


		return filteredStore;

	}
}
