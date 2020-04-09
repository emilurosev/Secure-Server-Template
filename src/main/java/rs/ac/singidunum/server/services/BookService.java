package rs.ac.singidunum.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.server.entities.Book;
import rs.ac.singidunum.server.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll(); 
	}
	
	public void deleteAllBooks() {
		bookRepository.deleteAll();
	}
	
}
