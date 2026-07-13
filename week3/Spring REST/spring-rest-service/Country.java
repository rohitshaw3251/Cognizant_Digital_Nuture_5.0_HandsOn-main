package rest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Country {
    @Id
    @NotNull
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    private String code;

    @NotNull
    @Size(min = 2, message = "Country name must be at least 2 characters")
    private String name;

    public Country() {}

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
