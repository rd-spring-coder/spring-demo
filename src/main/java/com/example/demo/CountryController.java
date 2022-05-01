package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CountryController {

    private CountryRepository countryRepository;
    @Autowired
    public CountryController(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @GetMapping("/country")
    public ResponseEntity<List<Country>> retrieveAllCountries(){
        log.info("Retrieving all countries");
        return ResponseEntity.status(HttpStatus.OK).body(countryRepository.findAll());
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code){
        log.info("Retrieving country with code {}", code);
        Optional<Country> country = countryRepository.findOne(QCountry.country.code.eq(code));
        if(country.isPresent()){
            log.debug("Found a country with country code {}", code);
            return ResponseEntity.status(HttpStatus.OK).body(country.get());
        }
        log.error("Cannot find a country with code {}", code);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/country")
    public ResponseEntity saveCountry(@RequestBody Country country){
        countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
