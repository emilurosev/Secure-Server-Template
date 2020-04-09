package rs.ac.singidunum.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.server.services.BookImportService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookImportController {
	
	@Autowired
	BookImportService bookImportService;
	
	@PostMapping("/importbooks")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<String> importBooks(@RequestBody String excelFilePath) {
		bookImportService.readBooksFromExcelFile(excelFilePath);
		return new ResponseEntity<String>("Imported", HttpStatus.OK);
	}

}
