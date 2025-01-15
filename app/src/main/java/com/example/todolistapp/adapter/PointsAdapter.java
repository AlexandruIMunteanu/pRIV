package com.example.todolistapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todolistapp.R;
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

    @Override
    public void onBindViewHolder(@NonNull PointViewHolder holder, int position) {
        holder.bind(points.get(position), position);
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    class PointViewHolder extends RecyclerView.ViewHolder {
        private TextView pointText;
        private ImageButton deleteButton;

        PointViewHolder(View itemView) {
            super(itemView);
            pointText = itemView.findViewById(R.id.pointText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

        void bind(String point, int position) {
            pointText.setText(point);
            deleteButton.setOnClickListener(v -> listener.onDelete(position));
        }
    }
} 