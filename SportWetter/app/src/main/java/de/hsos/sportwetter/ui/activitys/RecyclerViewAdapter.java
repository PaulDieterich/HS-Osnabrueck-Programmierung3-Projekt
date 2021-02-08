package de.hsos.sportwetter.ui.activitys;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import de.hsos.sportwetter.R;
import de.hsos.sportwetter.classes.activity.Activity;

import java.util.List;
//Help from: https://developer.android.com/guide/topics/ui/layout/recyclerview
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    private final List<Activity> mValues;

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
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getActivity_Name().setText(mValues.get(position).getName());
        holder.getActivity_Ort().setText(mValues.get(position).getStart().getPlaceName());
        holder.getActivity_Sport().setText(mValues.get(position).getArt().getName());
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