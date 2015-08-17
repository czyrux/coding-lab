package de.czyrux.codinglab.core.rest;


import java.util.List;

import de.czyrux.codinglab.domain.Country;
import de.czyrux.codinglab.domain.CountryRepository;

public class RestCountryRepository implements CountryRepository {

    private final CountryApi restApi;

    protected RestCountryRepository(CountryApi countryApi) {
        this.restApi = countryApi;
    }

    @Override
    public List<Country> getAllCountries() {
        return restApi.getAllCountries();
    }

    @Override
    public Country getCountryByCode(String countryCode) {

        Country country = null;
        List<Country> countryList = getCountriesByCode(new String[]{countryCode});
        if (countryList != null && countryList.size() >= 1) {
            country = countryList.get(0);
        }

        return country;
    }

    @Override
    public List<Country> getCountriesByCode(String[] countryCodes) {

        StringBuilder codesQuery = new StringBuilder();
        for (String code : countryCodes) {
            if (codesQuery.length() != 0) {
                codesQuery.append(';');
            }

            codesQuery.append(code);
        }

        return restApi.getCountriesByCodes(codesQuery.toString());
    }

    @Override
    public List<Country> getCountriesByRegion(String region) {
        return restApi.getCountriesByRegion(region);
    }

    @Override
    public List<Country> getCountriesByLanguage(String language) {
        return restApi.getCountriesByLanguage(language);
    }

}
