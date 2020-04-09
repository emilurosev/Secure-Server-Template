package rs.ac.singidunum.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.server.entities.Book;
import rs.ac.singidunum.server.services.BookService;

@RestController
@CrossOrigin(origins = {"http://locahost:4200"})
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
	}
	
	@DeleteMapping("/books")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<String> deleteAllBooks() {
		bookService.deleteAllBooks();
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

}
