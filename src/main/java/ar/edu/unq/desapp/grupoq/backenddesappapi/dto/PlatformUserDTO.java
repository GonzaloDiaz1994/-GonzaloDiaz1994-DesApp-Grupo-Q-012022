package ar.edu.unq.desapp.grupoq.backenddesappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlatformUserDTO implements Serializable {

    private String name;
    private String lastName;
    private Integer quantityOfOperations;
    private Double reputation;

}