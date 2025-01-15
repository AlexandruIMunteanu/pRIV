package com.example.todolistapp.model;

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

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getPointsOfInterest() { return pointsOfInterest; }
    public void setPointsOfInterest(List<String> pointsOfInterest) { this.pointsOfInterest = pointsOfInterest; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
} 