package de.hsos.sportwetter.ui.activitys;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;

import java.util.ArrayList;
import java.util.List;



/**
 * RecyclerViewAdapter
 * @author Paul Dieterich
 *
 * Help from: https://developer.android.com/guide/topics/ui/layout/recyclerview
 * */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    private List<Activity> mValues;

    public RecyclerViewAdapter(Activity context, List<Activity> items) {
        this.context = context;
        mValues = items;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getActivity_Name().setText(mValues.get(position).getName());
        holder.getActivity_Ort().setText(mValues.get(position).getStart().getPlaceName());
        holder.getActivity_Sport().setText(mValues.get(position).getArt().getName());
        holder.itemView.setOnClickListener(v -> {
            ActivityInfoArgs args = new ActivityInfoArgs.Builder(mValues.get(position).getId()).build();
            Navigation.findNavController(v).navigate(R.id.action_activityFragment_to_activtyInfo,args.toBundle());
        });
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView activity_Name;
        public final TextView activity_Sport;
        public final TextView activity_Ort;
        public ViewHolder(View view) {
            super(view);
            activity_Name = view.findViewById(R.id.activityName);
            activity_Sport = view.findViewById(R.id.sportName);
            activity_Ort = view.findViewById(R.id.activityOrt);
        }

        public TextView getActivity_Name() {
            return activity_Name;
        }
        public TextView getActivity_Sport() {
            return activity_Sport;
        }
        public TextView getActivity_Ort() {
            return activity_Ort;
        }


    }

}