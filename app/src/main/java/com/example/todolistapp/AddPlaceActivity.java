package com.example.todolistapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.todolistapp.databinding.ActivityAddEditPlaceBinding;
import com.example.todolistapp.model.TouristPlace;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class AddPlaceActivity extends AppCompatActivity {
    private ActivityAddEditPlaceBinding binding;
    private FirebaseFirestore db;
    private List<String> pointsOfInterest = new ArrayList<>();
    private PointsAdapter pointsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditPlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        setupPointsList();
        setupButtons();
    }

    private void setupPointsList() {
        pointsAdapter = new PointsAdapter(pointsOfInterest, position -> {
            pointsOfInterest.remove(position);
            pointsAdapter.notifyItemRemoved(position);
        });
        binding.pointsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.pointsRecyclerView.setAdapter(pointsAdapter);
    }

    private void setupButtons() {
        binding.addPointButton.setOnClickListener(v -> {
            String point = binding.pointInput.getText().toString().trim();
            if (!point.isEmpty()) {
                pointsOfInterest.add(point);
                pointsAdapter.notifyItemInserted(pointsOfInterest.size() - 1);
                binding.pointInput.setText("");
            }
        });

        binding.saveButton.setOnClickListener(v -> savePlace());
    }

    private void savePlace() {
        String country = binding.countryInput.getText().toString().trim();
        String city = binding.cityInput.getText().toString().trim();
        String description = binding.descriptionInput.getText().toString().trim();

        if (country.isEmpty() || city.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Completați toate câmpurile", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        TouristPlace place = new TouristPlace(country, city, description, 
            new ArrayList<>(pointsOfInterest), userId);

        db.collection("places")
            .add(place)
            .addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Loc adăugat cu succes", Toast.LENGTH_SHORT).show();
                finish();
            })
            .addOnFailureListener(e -> 
                Toast.makeText(this, "Eroare: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
    }
} 