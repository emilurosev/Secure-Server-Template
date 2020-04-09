package rs.ac.singidunum.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.server.entities.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
