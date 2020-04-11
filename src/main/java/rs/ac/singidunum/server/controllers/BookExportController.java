package rs.ac.singidunum.server.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.server.services.BookExportService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookExportController {
	
	@Autowired
	BookExportService bookExportService;
	
	@GetMapping("/exportbooks")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<String> export() throws IOException {
		bookExportService.export();
		return new ResponseEntity<String>("Exported", HttpStatus.OK);
	}

}
