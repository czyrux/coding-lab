package de.czyrux.codinglab.core.rest;

import java.util.List;

import de.czyrux.codinglab.domain.Country;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CountryApi {

    @GET("/all")
    List<Country> getAllCountries();

    @GET("/alpha")
    List<Country> getCountriesByCodes(@Query("codes") String codes);

    @GET("/region/{region}")
    List<Country> getCountriesByRegion(@Path("region") String region);

    @GET("/lang/{language}")
    List<Country> getCountriesByLanguage(@Path("language") String language);
}
