package rs.ac.singidunum.server.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Book {
	
	@Id
	private String id;
	
	private String title;
	private String author;
	private double price;
	private Date publicationDate;
	
	public Book(String title, String author, double price, Date publicationDate) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.publicationDate = publicationDate;
	}
	
	public Book() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
}
