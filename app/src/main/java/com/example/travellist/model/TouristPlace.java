package com.example.travellist.model;

import java.io.Serializable;
import java.util.List;

public class TouristPlace implements Serializable {
    private String id;
    private String country;
    private String city;
    private String description;
    private List<String> pointsOfInterest;
    private String userId;

    public TouristPlace() {}

    public TouristPlace(String country, String city, String description, List<String> pointsOfInterest, String userId) {
        this.country = country;
        this.city = city;
        this.description = description;
        this.pointsOfInterest = pointsOfInterest;
        this.userId = userId;
    }

    // Getters and setters remain the same
} 