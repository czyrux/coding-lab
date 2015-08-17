package de.czyrux.codinglab.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import de.czyrux.codinglab.R;
import de.czyrux.codinglab.domain.Country;
import de.czyrux.codinglab.service.DataFetcherIntentService;

public class CountryListActivity extends AppCompatActivity {
    public static final String TAG = "CountryListActivity";

    private List<Country> countryList = new ArrayList<>();
    private ResponseReceiver responseReceiver;

    private ListView countryListView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_country_list);

        countryListView = (ListView) findViewById(R.id.country_list);
        progressBar = (ProgressBar) findViewById(R.id.country_list_progressbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        responseReceiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(responseReceiver, new IntentFilter(ResponseReceiver.ACTION_RESP));

        if (countryList.isEmpty()) {
            showProgressBar();
            Intent serviceIntent = DataFetcherIntentService.newIntent(this, ResponseReceiver.ACTION_RESP, ResponseReceiver.DATA_RESP);
            startService(serviceIntent);
        } else {
            hideProgressBar();
            populateItems();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(responseReceiver);
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void populateItems() {
        CountryAdapter adapter = new CountryAdapter();
        adapter.setCountries(countryList);
        countryListView.setAdapter(adapter);
        hideProgressBar();
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "de.czyrux.codinglab.intent.action.COUNTRY_LIST";
        public static final String DATA_RESP = "de.czyrux.codinglab.intent.data.COUNTRY_LIST";

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Country> response = intent.getParcelableArrayListExtra(DATA_RESP);
            countryList.addAll(response);
            hideProgressBar();
            populateItems();
        }
    }

}
