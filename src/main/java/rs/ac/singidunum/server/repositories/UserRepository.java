package rs.ac.singidunum.server.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.server.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public User findByFirstName(String firstName);
	public List<User> findByLastName(String lastName);
	public User findByUsername(String username);
}
