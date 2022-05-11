package ar.edu.unq.desapp.grupoq.backenddesappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistryDTO implements Serializable {

    private String name;
    private String lastname;
    private String email;
    private String direction;
    private String password;
    private String mercadoPagoCVU;
    private String wallet;

}
