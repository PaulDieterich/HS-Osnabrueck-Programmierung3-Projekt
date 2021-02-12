package de.hsos.sportwetter.ui.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.weather.City;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    City context;
    private List<City> list;
    private List<City> copyList;
    public RecyclerViewAdapter(City context, List<City> items){
        this.context = context;
        list = items;
        copyList = new ArrayList<>(items);
    }
    @NotNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_city_item,parent,false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getCityName().setText(list.get(position).getName());
        holder.getCountryName().setText(list.get(position).getLand());
    }
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {

        return listFilter;
    }
    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<City> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(copyList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(City item : copyList){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView cityName;
        public final TextView countryName;


        public ViewHolder(View view) {
            super(view);
            countryName = view.findViewById(R.id.Land);
            cityName = view.findViewById(R.id.CityName);

        }

        public TextView getCityName() {
            return cityName;
        }
        public TextView getCountryName(){ return countryName;}
    }
}
