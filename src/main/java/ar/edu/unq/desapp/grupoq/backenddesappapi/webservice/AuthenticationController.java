package ar.edu.unq.desapp.grupoq.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.UserRegistryDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.mapper.UserMapper;
import ar.edu.unq.desapp.grupoq.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoq.backenddesappapi.security.jwt.JwtRequest;
import ar.edu.unq.desapp.grupoq.backenddesappapi.security.jwt.JwtResponse;
import ar.edu.unq.desapp.grupoq.backenddesappapi.security.jwt.JwtTokenUtil;
import ar.edu.unq.desapp.grupoq.backenddesappapi.service.JwtUserDetailsService;
import ar.edu.unq.desapp.grupoq.backenddesappapi.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Autowired
    private UserService userService;


    @PostMapping( "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        val response =  new JwtResponse(token(user(authenticationRequest.getUsername())));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private UserDetails user(String userName){
        return userDetailsService.loadUserByUsername(userName);
    }
    private String token(UserDetails userDetails){
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRegistryDTO userRegistryDTO) {
        User user = UserMapper.mapUserRegistryDTOToUserModel(userRegistryDTO);
        val response = userService.register(user);
        if (userService.getErrors().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

