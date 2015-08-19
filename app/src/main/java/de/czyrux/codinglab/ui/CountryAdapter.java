package de.czyrux.codinglab.ui;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import de.czyrux.codinglab.R;
import de.czyrux.codinglab.domain.Country;

public class CountryAdapter extends BaseAdapter {

    private final List<Country> countries = new ArrayList<>(20);

    public CountryAdapter() { }

    public void setCountries(final List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(final int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        Country country = (Country) getItem(position);

        // A cause of Android recycling views on adapters, this view may be initialized already.
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false);
        }

        TextView nameTextView = (TextView) parent.findViewById(R.id.country_item_name);
        nameTextView.setText(country.getName());

        return convertView;
    }
}
