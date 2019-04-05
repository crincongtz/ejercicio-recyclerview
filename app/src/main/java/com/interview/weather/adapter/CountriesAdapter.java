package com.interview.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interview.weather.R;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CityViewHolder> {

    private final int COUNTRY_NAME = 0;
    private final int STATE_LIST = 22;

    class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView country;
        private RecyclerView states;

        public CityViewHolder(View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.tv_country);
            states = itemView.findViewById(R.id.recycler_view_states);
        }
    }

    private Context mContext;
    private List<Object> countryList;

    public CountriesAdapter(Context context, List<Object> countries) {
        mContext = context;
        countryList = countries;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == COUNTRY_NAME) {
            return new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_country, parent, false));
        } else {
            return new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {

        if (countryList != null) {
            int itemType = getItemViewType(position);
            if (itemType == COUNTRY_NAME) {
                holder.country.setText((String) countryList.get(position));
            } else {
                RecyclerView recyclerView = holder.states;
                Context context = recyclerView.getContext();
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

                List<String> states = (List<String>) countryList.get(position);
                Collections.sort(states);

                StateAdapter stateAdapter = new StateAdapter(states);
                recyclerView.setAdapter(stateAdapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                stateAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (countryList.get(position) instanceof String) {
            return COUNTRY_NAME;
        } else {
            return STATE_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return countryList == null ? 0 : countryList.size();
    }


}
