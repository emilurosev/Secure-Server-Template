package rs.ac.singidunum.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import rs.ac.singidunum.server.entities.User;
import rs.ac.singidunum.server.repositories.UserRepository;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();

		userRepository.save(new User("Alice", "Smith", passwordEncoder.encode("password1"), new String[]{"doctor"}) );
		userRepository.save(new User("Bob", "Smith", passwordEncoder.encode("password2"), new String[]{"admin"}));
		userRepository.save(new User("John", "Connor", passwordEncoder.encode("password3"), new String[]{"police"}) );

		System.out.println("Users found with findAll():");
		System.out.println("-------------------------------");
		for (User user : userRepository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

	}

}
