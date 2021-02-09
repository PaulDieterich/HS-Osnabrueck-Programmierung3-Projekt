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
import de.hsos.sportwetter.classes.weather.City;


public class RecyclerViewAdapter {
    City context;
    private final List<City> list;

    public RecyclerViewAdapter(City context, List<City> items){
        this.context = context;
        list = items;
    }
    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_city_item,parent,false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getCityName().setText(list.get(position).getName());

    }
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView cityName;


        public ViewHolder(View view) {
            super(view);
            cityName = view.findViewById(R.id.stadtname);

        }

        public TextView getCityName() {
            return cityName;
        }

    }
}
