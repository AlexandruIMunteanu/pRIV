package com.example.travellist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.travellist.adapter.TouristPlaceAdapter;
import com.example.travellist.databinding.ActivityMainBinding;
import com.example.travellist.model.TouristPlace;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TouristPlaceAdapter.OnPlaceClickListener {
    private ActivityMainBinding binding;
    private FirebaseFirestore db;
    private TouristPlaceAdapter adapter;
    private List<TouristPlace> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        
        db = FirebaseFirestore.getInstance();
        setupRecyclerView();
        loadPlaces();

        binding.fabAdd.setOnClickListener(v -> 
            startActivity(new Intent(this, AddPlaceActivity.class))
        );
    }

    private void setupRecyclerView() {
        adapter = new TouristPlaceAdapter(places, this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void loadPlaces() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("places")
            .whereEqualTo("userId", userId)
            .addSnapshotListener((value, error) -> {
                if (error != null) return;
                places.clear();
                if (value != null) {
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        TouristPlace place = doc.toObject(TouristPlace.class);
                        place.setId(doc.getId());
                        places.add(place);
                    }
                }
                adapter.updatePlaces(places);
            });
    }

    @Override
    public void onEditClick(TouristPlace place) {
        Intent intent = new Intent(this, EditPlaceActivity.class);
        intent.putExtra("place", place);
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(TouristPlace place) {
        db.collection("places").document(place.getId())
            .delete()
            .addOnSuccessListener(aVoid -> {
                // Ștergerea va fi reflectată automat prin SnapshotListener
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}