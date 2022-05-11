package ar.edu.unq.desapp.grupoq.backenddesappapi.persistance;

import ar.edu.unq.desapp.grupoq.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Boolean existsByWallet(String wallet);
}