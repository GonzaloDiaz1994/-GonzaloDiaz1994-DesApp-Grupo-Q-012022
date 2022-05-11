package ar.edu.unq.desapp.grupoq.backenddesappapi.mapper;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.PlatformUserDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.model.User;

public class UserMapper {

    public static PlatformUserDTO mapModelToDTO(User user) {
        return PlatformUserDTO.builder()
                .name(user.getName())
                .lastName(user.getLastname())
                .quantityOfOperations(0)
                .reputation(0d).build();
    }

}
