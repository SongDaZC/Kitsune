package com.kitsune.project.rest;

import java.util.Date;
import java.util.List;

public class HealthData {
    private String username;
    private List<Integer> usualSigns;
    private List<Integer> unusualSigns;
    private int sleepTime;
    private String tcmObservation;

    public HealthData(String username, List<Integer> usualSigns, List<Integer> unusualSigns, int sleepTime, String tcmObservation) {
        this.username = username;
        this.usualSigns = usualSigns;
        this.unusualSigns = unusualSigns;
        this.sleepTime = sleepTime;
        this.tcmObservation = tcmObservation;
    }

    public int getScore() {
        int usualScore = usualSigns.stream().mapToInt(Integer::intValue).sum();
        int unusualScore = -1 * unusualSigns.stream().mapToInt(Integer::intValue).sum();
        return usualScore + unusualScore;
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
}

