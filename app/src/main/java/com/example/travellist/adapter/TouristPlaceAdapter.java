package com.example.travellist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travellist.databinding.ItemTouristPlaceBinding;
import com.example.travellist.model.TouristPlace;
import java.util.List;

public class TouristPlaceAdapter extends RecyclerView.Adapter<TouristPlaceAdapter.TouristPlaceViewHolder> {
    private List<TouristPlace> places;
    private final OnPlaceClickListener listener;

    public interface OnPlaceClickListener {
        void onEditClick(TouristPlace place);
        void onDeleteClick(TouristPlace place);
    }

    public TouristPlaceAdapter(List<TouristPlace> places, OnPlaceClickListener listener) {
        this.places = places;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TouristPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTouristPlaceBinding binding = ItemTouristPlaceBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new TouristPlaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristPlaceViewHolder holder, int position) {
        holder.bind(places.get(position));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void updatePlaces(List<TouristPlace> newPlaces) {
        this.places = newPlaces;
        notifyDataSetChanged();
    }

    class TouristPlaceViewHolder extends RecyclerView.ViewHolder {
        private final ItemTouristPlaceBinding binding;

        TouristPlaceViewHolder(ItemTouristPlaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TouristPlace place) {
            binding.countryCity.setText(place.getCountry() + ", " + place.getCity());
            binding.description.setText(place.getDescription());
            binding.pointsOfInterest.setText("Puncte de interes: " + 
                String.join(", ", place.getPointsOfInterest()));

            binding.editButton.setOnClickListener(v -> listener.onEditClick(place));
            binding.deleteButton.setOnClickListener(v -> listener.onDeleteClick(place));
        }
    }
} 