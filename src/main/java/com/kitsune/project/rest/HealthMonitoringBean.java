package com.kitsune.project.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HealthMonitoringBean {

    private String url = "jdbc:postgresql://localhost:5432/fox";
    private String user = "fox";
    private String password = "sava";

    public HealthMonitoringBean() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addUser(String username) {
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (username) VALUES (?) ON CONFLICT (username) DO NOTHING");
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addScore(String username, HealthData healthData) {
        try (Connection connection = getConnection()) {
            int score = calculateScore(healthData.getUsualSigns(), healthData.getUnusualSigns(), healthData.getSleepTime());
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO scores (username, score, date) VALUES (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setInt(2, score);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<HealthData> getScores(String username) {
        List<HealthData> scores = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT score, sleep_time, date FROM scores WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                List<Integer> usualSigns = getUsualSigns(rs.getInt("score"));
                List<Integer> unusualSigns = getUnusualSigns(rs.getInt("score"));
                int sleepTime = rs.getInt("sleep_time");
                String tcmObservation = getTcmObservation(rs.getDate("date"));
                HealthData healthData = new HealthData(username, rs.getInt("score"));
                healthData.setUsualSigns(usualSigns);
                healthData.setUnusualSigns(unusualSigns);
                healthData.setSleepTime(sleepTime);
                healthData.setTcmObservation(tcmObservation);
                scores.add(healthData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    private int calculateScore(List<Integer> usualSigns, List<Integer> unusualSigns, int
sleepTime) {
int usualSignsScore = 0;
int unusualSignsScore = 0;


    for (int sign : usualSigns) {
        usualSignsScore += sign;
    }

    for (int sign : unusualSigns) {
        unusualSignsScore += sign;
    }

    int score = usualSignsScore + unusualSignsScore - (sleepTime / 60);

    if (score < 0) {
        return 0;
    } else if (score > 100) {
        return 100;
    } else {
        return score;
    }
}

private List<Integer> getUsualSigns(int score) {
    List<Integer> usualSigns = new ArrayList<>();
    int tempScore = score;

    while (tempScore >= 2) {
        usualSigns.add(2);
        tempScore -= 2;
    }

    if (tempScore == 1) {
        usualSigns.add(1);
    }

    while (usualSigns.size() < 10) {
        usualSigns.add(0);
    }

    return usualSigns;
}

private List<Integer> getUnusualSigns(int score) {
    List<Integer> unusualSigns = new ArrayList<>();
    int tempScore = Math.abs(score);

    while (tempScore >= 2) {
        unusualSigns.add(-2);
        tempScore -= 2;
    }

    if (tempScore == 1) {
        unusualSigns.add(-1);
    }

    while (unusualSigns.size() < 10) {
        unusualSigns.add(0);
    }

    return unusualSigns;
}

private String getTcmObservation(Date date) {
    // TODO: implement TCM observation logic
    return "";
}

// Add more methods for handling user data and performing calculations
}

		



