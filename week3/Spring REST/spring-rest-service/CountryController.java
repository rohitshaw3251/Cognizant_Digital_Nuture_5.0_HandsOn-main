package rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @GetMapping("/{code}")
    public Country getByCode(@PathVariable String code) {
        return countryRepository.findById(code)
            .orElseThrow(() -> new ResourceNotFoundException("Country with code " + code + " not found"));
    }

    @PostMapping
    public Country create(@Valid @RequestBody Country country) {
        return countryRepository.save(country);
    }

    @PutMapping("/{code}")
    public Country update(@PathVariable String code, @Valid @RequestBody Country country) {
        Country existing = countryRepository.findById(code)
            .orElseThrow(() -> new ResourceNotFoundException("Country with code " + code + " not found"));
        existing.setName(country.getName());
        return countryRepository.save(existing);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        Country existing = countryRepository.findById(code)
            .orElseThrow(() -> new ResourceNotFoundException("Country with code " + code + " not found"));
        countryRepository.delete(existing);
    }
}
