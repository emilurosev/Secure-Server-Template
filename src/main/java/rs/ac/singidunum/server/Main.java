package rs.ac.singidunum.server;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import rs.ac.singidunum.server.entities.Book;
import rs.ac.singidunum.server.entities.User;
import rs.ac.singidunum.server.repositories.BookRepository;
import rs.ac.singidunum.server.repositories.UserRepository;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		bookRepository.deleteAll();

		userRepository.save(new User("user", passwordEncoder.encode("user"), new String[] { "user" }));
		userRepository.save(new User("admin", passwordEncoder.encode("admin"), new String[] { "admin", "user" }));

		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		bookRepository.save(new Book("Book 1", "Author 1", 999.99, simpleDateFormat.parse("12-01-2018")));
		bookRepository.save(new Book("Book 2", "Author 2", 899.99, simpleDateFormat.parse("12-01-2017")));
		bookRepository.save(new Book("Book 3", "Author 3", 799.99, simpleDateFormat.parse("12-01-2016")));
		bookRepository.save(new Book("Book 4", "Author 4", 699.99, simpleDateFormat.parse("12-01-2015")));
		bookRepository.save(new Book("Book 5", "Author 5", 599.99, simpleDateFormat.parse("12-01-2014")));
		bookRepository.save(new Book("Book 6", "Author 6", 499.99, simpleDateFormat.parse("12-01-2013")));
		bookRepository.save(new Book("Book 7", "Author 7", 399.99, simpleDateFormat.parse("12-01-2012")));

		System.out.println("Users found with findAll():");
		System.out.println("-------------------------------");
		for (User user : userRepository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

	}
	
}
