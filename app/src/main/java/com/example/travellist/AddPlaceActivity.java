package com.example.travellist;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.travellist.adapter.PointsAdapter;
import com.example.travellist.databinding.ActivityAddEditPlaceBinding;
import com.example.travellist.model.TouristPlace;
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
} 