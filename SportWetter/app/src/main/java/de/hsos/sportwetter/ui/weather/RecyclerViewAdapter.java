package de.hsos.sportwetter.ui.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.Weather;

public class RecyclerViewAdapter {
    Weather context;
    private final List<Weather> list;

    public RecyclerViewAdapter(Weather context, List<Weather> items){
        this.context = context;
        list = items;
    }
    @NotNull
    @Override
    public de.hsos.sportwetter.ui.activitys.RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_weather_item,parent,false);
        return ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getCityName().setText(list.get(position).getStadtname());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView cityName;


        public ViewHolder(View view) {
            super(view);
            cityName = view.findViewById(R.id.cityName);

        }

        public TextView getCityName() {
            return cityName;
        }

    }
}
