package bdd;

import java.util.Calendar;
import java.util.Date;

public class Book {
	private String title;
	private String author;
	private Calendar published;
	private String date;
 
	// constructors, getter, setter ommitted
	
	public Book(String title,String author,Calendar published)
	{
		setAuthor(author);
		setTitle(title);
		setPublished(published);
	}

	public Book(String title2, String author2, String date) {
		// TODO Auto-generated constructor stub
		setAuthor(author2);
		setTitle(title2);
		setDate(date);
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Calendar getPublished() {
		return published;
	}
	
	
	public String getDate()
	{
		return date;
	}
	public void setTitle(String title)
	{
		 this.title = title;
	}
	
	public void setAuthor(String author)
	{
		 this.author = author;
	}
	
	public void setPublished(Calendar published)
	{
		 this.published = published;
	}
	
	public void setDate(String published)
	{
		 this.date = published;
	}
}