package de.czyrux.codinglab.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.czyrux.codinglab.R;
import de.czyrux.codinglab.domain.Country;


public class CountryAdapter extends BaseAdapter {

    private final List<Country> countries;

    public CountryAdapter() {
        this.countries = new ArrayList<>(20);
    }

    public void addCountries(List<Country> countries) {
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    public void setCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Country country = (Country) getItem(position);

        CountryViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false);
            viewHolder = new CountryViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CountryViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(country.getName());

        return convertView;
    }


    static class CountryViewHolder {
        TextView name;

        CountryViewHolder(View parent) {
            name = (TextView) parent.findViewById(R.id.country_item_name);
        }
    }
}
