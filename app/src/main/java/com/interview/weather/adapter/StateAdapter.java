package com.interview.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.interview.weather.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateItemViewHolder> {

    private List<String> states;

    public StateAdapter(List<String> stateList) {
        this.states = stateList;
    }

    @Override
    public StateItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StateItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state, parent, false));
    }

    @Override
    public void onBindViewHolder(StateItemViewHolder holder, int position) {
        if (states != null) {
            String myState = states.get(position);
            holder.tvState.setText(myState);
        }
    }

    @Override
    public int getItemCount() {
        return states == null ? 0 : states.size();
    }

    class StateItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvState;

        StateItemViewHolder(View itemView) {
            super(itemView);
            tvState = itemView.findViewById(R.id.tv_state);
        }
    }

}
