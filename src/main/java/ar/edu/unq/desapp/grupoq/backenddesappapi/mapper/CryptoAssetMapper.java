package ar.edu.unq.desapp.grupoq.backenddesappapi.mapper;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.CryptoAssetDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.model.CryptoAsset;

import java.util.Date;

public class CryptoAssetMapper {

    public static CryptoAssetDTO mapModelToDTO(CryptoAsset cryptoAsset) {
        String date = new Date(System.currentTimeMillis()).toString();
        return CryptoAssetDTO.builder()
                .cryptoAsset(cryptoAsset.getSymbol())
                .date(date)
                .price(cryptoAsset.getPrice()).build();
    }
}
