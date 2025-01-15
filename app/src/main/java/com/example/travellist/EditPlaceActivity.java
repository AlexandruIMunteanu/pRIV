package com.example.travellist;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.travellist.adapter.PointsAdapter;
import com.example.travellist.databinding.ActivityAddEditPlaceBinding;
import com.example.travellist.model.TouristPlace;
import com.google.firebase.firestore.FirebaseFirestore;
// Restul codului rămâne la fel 