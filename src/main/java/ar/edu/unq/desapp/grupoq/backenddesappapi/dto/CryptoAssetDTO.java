package ar.edu.unq.desapp.grupoq.backenddesappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CryptoAssetDTO {

    private String cryptoAsset;
    private String date;
    private Float price;

}
