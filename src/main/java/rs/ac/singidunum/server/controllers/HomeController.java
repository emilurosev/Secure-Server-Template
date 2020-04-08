package rs.ac.singidunum.server.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "<h1>Home Page</h1>";
	}

	@GetMapping("/user")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<HashMap<String, Object>> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("Principal", authentication.getPrincipal());
		hashMap.put("Details", authentication.getDetails());
		return new ResponseEntity<HashMap<String,Object>>(hashMap, HttpStatus.OK);
	}

	@GetMapping("/admin")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> getPolicePage() {
		return new ResponseEntity<String>("<h1>Only admin</h1>", HttpStatus.OK);
	}

}