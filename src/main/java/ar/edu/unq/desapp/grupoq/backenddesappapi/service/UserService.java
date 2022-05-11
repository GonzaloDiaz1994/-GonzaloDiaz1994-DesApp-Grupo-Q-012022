package ar.edu.unq.desapp.grupoq.backenddesappapi.service;

import ar.edu.unq.desapp.grupoq.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoq.backenddesappapi.persistance.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, String> errors = new LinkedHashMap<>();
    private final String error = "ERROR";

    public Map<String, String> register(User user) {
        if(Boolean.TRUE.equals(this.areValidData(user))){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            Map<String, String> info = new LinkedHashMap<>();
            info.put("info", "Registered user successfully");
            return info;
        }else {
            return this.errors;
        }
    }

    public Boolean areValidData(User user) {
        return this.validNameAndLastName(user.getName(), user.getLastname()) &&
                this.validCVU(user.getMercadoPagoCVU()) &&
                this.validWallet(user.getWallet()) &&
                this.validateEmail(user.getEmail()) &&
                this.validPassword(user.getPassword());
    }

    public Boolean validNameAndLastName(String name, String lastName) {
        val isValidName = 3 <= name.length() && name.length() <= 30;
        val isValidLastName = 3 <= lastName.length() && lastName.length() <= 30;
        if (!isValidName) {
            this.errors.put(error, "Name size must be between 3 and 30");
        }
        if (!isValidLastName) {
            this.errors.put(error, "LastName size mut be between 3 and 30");
        }
        return isValidName && isValidLastName;
    }


    public Boolean validCVU(String cvu) {
        val isValid = cvu.length() == 22;
        if (!isValid) {
            this.errors.put(error, "The MercadoPago cvu must contain 22 digits");
        }
        return isValid;
    }

    public Boolean validWallet(String wallet) {
        val isValid = wallet.length() == 8;
        val exist = this.userRepository.existsByWallet(wallet);
        if (!isValid) {
            this.errors.put(error, "The wallet must contain 8 digits");
        } else if (exist) {
            this.errors.put(error, "This wallet already exists");
        }
        return isValid && !exist;
    }

    public Boolean validPassword(String password) {
        String passPattern = "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$";
        val isValid = password.matches(passPattern) && (!password.isEmpty() || !password.isBlank()) ;
        if (!isValid) {
            this.errors.put(error, "Invalid password");
        }
        return isValid;
    }

    public Boolean validateEmail(String email){
        Boolean existEmail = existEmail(email);
        // expresion regular
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Boolean validEmail = email.matches(emailPattern);
        Boolean emailNoIsEmpty = !email.isEmpty();

        if(Boolean.FALSE.equals(!validEmail)){
            this.errors.put(error, "Email invalid");
        } else if(Boolean.TRUE.equals(existEmail)){
            this.errors.put(error, "Email already exists");
        }
        return emailNoIsEmpty && validEmail && !existEmail;
    }

    public Boolean existEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Map<String, String> getErrors () {
        return this.errors;
    }
}