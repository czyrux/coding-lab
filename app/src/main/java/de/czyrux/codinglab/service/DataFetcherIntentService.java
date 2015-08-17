package de.czyrux.codinglab.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

import de.czyrux.codinglab.CountryApplication;
import de.czyrux.codinglab.domain.Country;
import de.czyrux.codinglab.domain.CountryRepository;

public class DataFetcherIntentService extends IntentService {

    private static final String EXTRA_ACTION_RESPONSE = "EXTRA_ACTION_RESPONSE";
    private static final String EXTRA_DATA_RESPONSE = "EXTRA_DATA_RESPONSE";

    public static Intent newIntent(Context context, String actionResponseKey, String dataResponseKey) {
        Intent serviceIntent = new Intent(context, DataFetcherIntentService.class);
        serviceIntent.putExtra(EXTRA_ACTION_RESPONSE, actionResponseKey);
        serviceIntent.putExtra(EXTRA_DATA_RESPONSE, dataResponseKey);
        return serviceIntent;
    }


    public DataFetcherIntentService() {
        super("DataFetcherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        CountryRepository repository = ((CountryApplication) getApplication()).getCountryRepository();
        Log.e("DataFetcher", "go go");

        ArrayList<Country> countryList = new ArrayList<>(repository.getAllCountries());

        String actionResponse = intent.getStringExtra(EXTRA_ACTION_RESPONSE);
        String dataResponse = intent.getStringExtra(EXTRA_DATA_RESPONSE);
        Intent responseIntent = new Intent();
        responseIntent.setAction(actionResponse);
        responseIntent.putParcelableArrayListExtra(dataResponse, countryList);

        LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(responseIntent);
    }
}
