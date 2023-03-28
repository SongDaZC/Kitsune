package com.kitsune.project.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/health")
public class HealthMonitoringResource {

    private HealthMonitoringBean healthMonitoringBean = new HealthMonitoringBean();

    @GET
    @Path("/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HealthData> getHealthData(@PathParam("username") String username) {
        return healthMonitoringBean.getScores(username);
    }

    @POST
    @Path("/user/{username}/score")
    public void saveHealthData(@PathParam("username") String username, HealthData data) {
    	healthMonitoringBean.addScore(username, data);
    }
}

