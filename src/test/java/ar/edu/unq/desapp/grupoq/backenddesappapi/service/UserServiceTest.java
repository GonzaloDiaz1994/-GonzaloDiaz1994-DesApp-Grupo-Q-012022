package ar.edu.unq.desapp.grupoq.backenddesappapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void isValidNameAndLastName() {
        String name = "Gonzalo";
        String lastName = "Diaz";
        Assertions.assertTrue(userService.validNameAndLastName(name, lastName));
    }

    @Test
    public void isNotValidNameAndLastName() {
        String name = "Gonzalo Felipe Fernando Juan Pablo Arnaldo";
        String lastName = "Diaz";
        Assertions.assertFalse(userService.validNameAndLastName(name, lastName));
        name = "Gonzalo";
        lastName = "Diaz Gonzales Ramirez Miranda Messi Ronaldo Martinez";
        Assertions.assertFalse(userService.validNameAndLastName(name, lastName));
    }

    @Test
    public void isValidCVU() {
        String cvu = "adfg-qwer-zxcv-ghj-123";
        Assertions.assertTrue(userService.validCVU(cvu));
    }

    @Test
    public void isNotValidCVU() {
        String cvu = "adfg-qwer-zxcv-ghj";
        Assertions.assertFalse(userService.validCVU(cvu));
    }

    @Test
    public void isValidWallet() {
        String wallet = "wallet12";
        Assertions.assertTrue(userService.validWallet(wallet));
    }

    @Test
    public void isNotValidWallet() {
        String wallet = "wallet";
        Assertions.assertFalse(userService.validWallet(wallet));
    }

}
