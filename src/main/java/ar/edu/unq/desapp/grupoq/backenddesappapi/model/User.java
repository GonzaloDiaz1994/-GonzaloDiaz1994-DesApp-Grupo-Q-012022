package ar.edu.unq.desapp.grupoq.backenddesappapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "lastname", length = 30, nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "address", nullable = false, length = 30)
    private String direction;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "cvu", nullable = false, length = 22, unique = true)
    private String MercadoPagoCVU;
    @Column(name = "wallet", nullable = false, length = 8, unique = true)
    private String wallet;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
