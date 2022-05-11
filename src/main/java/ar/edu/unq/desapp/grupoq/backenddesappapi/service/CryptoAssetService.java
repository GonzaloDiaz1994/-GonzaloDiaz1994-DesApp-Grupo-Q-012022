package ar.edu.unq.desapp.grupoq.backenddesappapi.service;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.CryptoAssetDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.mapper.CryptoAssetMapper;
import ar.edu.unq.desapp.grupoq.backenddesappapi.model.CryptoAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class CryptoAssetService {

    @Autowired
    private RestTemplate getRestTemplate;

    public CryptoAsset getPriceFromCryptoAsset(String name) {
        String url = "https://api1.binance.com/api/v3/ticker/price?symbol=@@symbol@@".replace("@@symbol@@", name);
        ResponseEntity<CryptoAsset> cryptoAsset = getRestTemplate.getForEntity(url, CryptoAsset.class);
        return cryptoAsset.getBody();
    }



}
