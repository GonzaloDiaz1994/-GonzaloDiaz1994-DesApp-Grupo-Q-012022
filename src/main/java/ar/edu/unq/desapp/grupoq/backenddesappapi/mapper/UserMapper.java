package ar.edu.unq.desapp.grupoq.backenddesappapi.mapper;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.PlatformUserDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.UserRegistryDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.model.User;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PlatformUserDTO mapModelToDTO(User user) {
        return PlatformUserDTO.builder()
                .name(user.getName())
                .lastName(user.getLastname())
                .quantityOfOperations(0)
                .reputation(0d).build();
    }

    public static User mapUserRegistryDTOToUserModel(UserRegistryDTO userRegistryDTO) {
        return User.builder()
                .name(userRegistryDTO.getName())
                .lastname(userRegistryDTO.getLastname())
                .direction(userRegistryDTO.getDirection())
                .email(userRegistryDTO.getEmail())
                .password(userRegistryDTO.getPassword())
                .mercadoPagoCVU(userRegistryDTO.getMercadoPagoCVU())
                .wallet(userRegistryDTO.getWallet()).build();
    }

}
