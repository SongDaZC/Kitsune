package com.kitsune.project.rest;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        HttpHandler handler = new HealthMonitoringHandler(); 
        server.createContext("/", handler);
        
        server.setExecutor(null);
        server.start();
        System.out.println("Server is listening on port " + port);
    }
}
