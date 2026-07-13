package ormlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String code) {
        return countryRepository.findCountryByCode(code);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountryByNameContaining(String name) {
        return countryRepository.findByNameContainingOrderByNameAsc(name);
    }

    @Transactional(readOnly = true)
    public List<Country> searchCountryByNameStartingWith(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String newName) {
        Country c = countryRepository.findById(code).orElse(null);
        if (c != null) {
            c.setName(newName);
            countryRepository.save(c);
        }
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }
}
