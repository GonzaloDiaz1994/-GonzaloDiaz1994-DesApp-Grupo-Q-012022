package ar.edu.unq.desapp.grupoq.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.PlatformUserDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.mapper.UserMapper;
import ar.edu.unq.desapp.grupoq.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public ResponseEntity<List<PlatformUserDTO>> users() {
        List<PlatformUserDTO> platformUserDTO = userService.findAllUsers().stream()
                .map(UserMapper::mapModelToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(platformUserDTO, HttpStatus.OK);
    }

}
