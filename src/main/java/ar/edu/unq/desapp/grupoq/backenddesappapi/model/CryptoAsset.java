package ar.edu.unq.desapp.grupoq.backenddesappapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CryptoAsset {

    private String symbol;
    private Float price;

}
