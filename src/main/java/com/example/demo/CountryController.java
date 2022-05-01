package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    private CountryRepository countryRepository;
    @Autowired
    public CountryController(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @GetMapping("/country")
    public ResponseEntity<List<Country>> retrieveAllCountries(){
        return ResponseEntity.status(HttpStatus.OK).body(countryRepository.findAll());
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code){
        Optional<Country> country = countryRepository.findOne(QCountry.country.code.eq(code));
        if(country.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(country.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/country")
    public ResponseEntity saveCountry(@RequestBody Country country){
        countryRepository.save(country);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
