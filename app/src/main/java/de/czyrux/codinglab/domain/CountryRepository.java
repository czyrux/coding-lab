package de.czyrux.codinglab.domain;

import java.util.List;

import de.czyrux.codinglab.domain.Country;

public interface CountryRepository {
    List<Country> getAllCountries();

    Country getCountryByCode(String countryCode);

    List<Country> getCountriesByCode(String[] countryCodes);

    List<Country> getCountriesByRegion(String region);

    List<Country> getCountriesByLanguage(String language);
}
