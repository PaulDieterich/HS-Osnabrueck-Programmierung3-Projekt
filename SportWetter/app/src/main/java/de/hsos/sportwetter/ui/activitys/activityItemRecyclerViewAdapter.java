package de.hsos.sportwetter.ui.activitys;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hsos.sportwetter.classes.activity.Activity;
import de.hsos.sportwetter.placeholder.PlaceholderContent.PlaceholderItem;
import de.hsos.sportwetter.databinding.FragmentActivityItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class activityItemRecyclerViewAdapter extends RecyclerView.Adapter<activityItemRecyclerViewAdapter.ViewHolder> {

    private final List<Activity> mValues;

    public activityItemRecyclerViewAdapter(List<Activity> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentActivityItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Activity activity = mValues.get(position);
        holder.mContentView.setText(activity.getName());
        holder.mIdView.setText(activity.getArt().toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mContentView2;
        public Activity mItem;

        public ViewHolder(FragmentActivityItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.activiryArt;
            mContentView = binding.ActiviryLocation;
            mContentView2 = binding.activiryName;
        }
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}