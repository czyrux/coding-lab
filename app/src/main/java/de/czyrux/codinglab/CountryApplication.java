package de.czyrux.codinglab;

import android.app.Application;

import de.czyrux.codinglab.core.rest.RestCountryRepositoryFactory;
import de.czyrux.codinglab.domain.CountryRepository;


public class CountryApplication extends Application {

    private CountryRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = RestCountryRepositoryFactory.create();
    }

    public CountryRepository getCountryRepository() {
        return repository;
    }
}
