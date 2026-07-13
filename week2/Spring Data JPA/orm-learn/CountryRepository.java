package ormlearn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByNameContainingOrderByNameAsc(String name);

    List<Country> findByNameStartingWith(String letter);

    @Query("SELECT c FROM Country c WHERE c.code = :code")
    Country findCountryByCode(@Param("code") String code);

    @Query(value = "SELECT * FROM country WHERE name LIKE :pattern", nativeQuery = true)
    List<Country> findCountriesByNamePattern(@Param("pattern") String pattern);
}
