package rs.ac.singidunum.server.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.server.authentication.AuthenticationRequest;
import rs.ac.singidunum.server.authentication.AuthenticationResponse;
import rs.ac.singidunum.server.dto.UserDTO;
import rs.ac.singidunum.server.entities.User;
import rs.ac.singidunum.server.repositories.UserRepository;
import rs.ac.singidunum.server.user.MyUserDetailsService;
import rs.ac.singidunum.server.utils.JwtUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		final String username = userDetails.getUsername();
		List<Object> response = new ArrayList<>();
		response.add(jwt);
		response.add(authorities);
		response.add(username);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, username, authorities));
	}

	@PostMapping("/register")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
		User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
				new String[] { userDTO.getRole() });
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}
