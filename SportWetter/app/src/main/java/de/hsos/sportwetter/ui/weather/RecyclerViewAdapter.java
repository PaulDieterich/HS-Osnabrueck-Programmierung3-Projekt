package de.hsos.sportwetter.ui.weather;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;
/**
 * RecyclerViewAdapter
 * @author Stefan Waschk
 * */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    City context;
    private Cursor cursor;
    public RecyclerViewAdapter(City context, Cursor c){
        this.context = context;

        cursor = c;
    }
    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_city_item,parent,false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //set cursor to next position
        cursor.moveToPosition(position);
        /**
         * holt sich die zeile name und country aus dem cursor und speichert
         * diese in die dafür vorgesehenden textfelder
         * */
        String cityName = cursor.getString(cursor.getColumnIndex("name"));
        String countryName = cursor.getString(cursor.getColumnIndex("country"));
        holder.getCityName().setText(cityName);
        holder.getCountryName().setText(countryName);
        holder.itemView.setOnClickListener(v -> {
            WeatherFragmentArgs args = new WeatherFragmentArgs.Builder().setCityName(cityName).build();
            Navigation.findNavController(v).navigate(R.id.action_addNewWeatherLocationFragment_to_weatherFragment,args.toBundle());
        });
    }
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView cityName;
        public final TextView countryName;


        public ViewHolder(View view) {
            super(view);
            countryName = view.findViewById(R.id.Land);
            cityName = view.findViewById(R.id.CityName);
            LinearLayout item = view.findViewById(R.id.item);
        }

        public TextView getCityName() {
            return cityName;
        }
        public TextView getCountryName(){ return countryName;}
    }
}
