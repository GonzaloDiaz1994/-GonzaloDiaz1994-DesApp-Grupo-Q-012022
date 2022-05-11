package ar.edu.unq.desapp.grupoq.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoq.backenddesappapi.dto.CryptoAssetDTO;
import ar.edu.unq.desapp.grupoq.backenddesappapi.mapper.CryptoAssetMapper;
import ar.edu.unq.desapp.grupoq.backenddesappapi.service.CryptoAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CryptoAssetController {

    @Autowired
    CryptoAssetService cryptoAssetService;

    @GetMapping("/crypto-asset")
    public ResponseEntity<?> getCryptoAsset(@RequestParam String symbol) {
        CryptoAssetDTO cryptoAssetDTO = CryptoAssetMapper
                .mapModelToDTO(cryptoAssetService.getPriceFromCryptoAsset(symbol));
        return new ResponseEntity<>(cryptoAssetDTO, HttpStatus.OK);
    }


}
