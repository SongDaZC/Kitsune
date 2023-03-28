package com.kitsune.project.rest;

import java.util.ArrayList;
import java.util.List;

public class HealthData {
    private String username;
    private List<Integer> usualSigns;
    private List<Integer> unusualSigns;
    private int sleepTime;
    private String tcmObservation;

    public HealthData(String username, int score) {
        this.username = username;
        this.usualSigns = new ArrayList<>(); // Initialize with an empty list
        this.unusualSigns = new ArrayList<>(); // Initialize with an empty list
        this.sleepTime = 0;
        this.tcmObservation = "Score: " + score;
    }

    public HealthData getScore() {
        int usualScore = usualSigns.stream().mapToInt(Integer::intValue).sum();
        int unusualScore = -1 * unusualSigns.stream().mapToInt(Integer::intValue).sum();
        int score = usualScore + unusualScore;
        return new HealthData(username, score);
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getUsualSigns() {
        return usualSigns;
    }

    public List<Integer> getUnusualSigns() {
        return unusualSigns;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public String getTcmObservation() {
        return tcmObservation;
    }
    
    
    public void setUsualSigns(List<Integer> usualSigns) {
        this.usualSigns = usualSigns;
    }

    public void setUnusualSigns(List<Integer> unusualSigns) {
        this.unusualSigns = unusualSigns;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setTcmObservation(String tcmObservation) {
        this.tcmObservation = tcmObservation;
    }

}

