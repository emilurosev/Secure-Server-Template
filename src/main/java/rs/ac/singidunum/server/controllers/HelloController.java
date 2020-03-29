package rs.ac.singidunum.server.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "<h1>Home Page</h1>";
	}

	@GetMapping("/user")
	@Secured({"ROLE_DOCTOR", "ROLE_POLICE", "ROLE_ADMIN"})
	public HashMap<String, Object> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("Principal", authentication.getPrincipal());
		hashMap.put("Details", authentication.getDetails());
		return hashMap;
	}

	@GetMapping("/doctor")
	@Secured({ "ROLE_DOCTOR", "ROLE_ADMIN" })
	public ResponseEntity<String> getDoctorPage() {
		return new ResponseEntity<String>("<h1>Only doctor</h1>", HttpStatus.OK);
	}

	@GetMapping("/police")
	@Secured({ "ROLE_POLICE", "ROLE_ADMIN" })
	public ResponseEntity<String> getPolicePage() {
		return new ResponseEntity<String>("<h1>Only police</h1>", HttpStatus.OK);
	}
	

}