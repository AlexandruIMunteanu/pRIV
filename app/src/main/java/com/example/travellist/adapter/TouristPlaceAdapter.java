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
} 