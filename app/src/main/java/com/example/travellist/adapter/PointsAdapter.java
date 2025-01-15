package com.example.travellist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travellist.R;
import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointViewHolder> {
    private List<String> points;
    private OnPointDeleteListener listener;

    public interface OnPointDeleteListener {
        void onDelete(int position);
    }

    public PointsAdapter(List<String> points, OnPointDeleteListener listener) {
        this.points = points;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_point, parent, false);
        return new PointViewHolder(view);
    }
} 