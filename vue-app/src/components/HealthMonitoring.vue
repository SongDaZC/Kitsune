<template>
  <div class="health-monitoring">
    <div class="header">
      <h2>Health Monitoring App</h2>
    </div>
    <div class="input-container">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username" />
      <button @click="getHealthData">Get Health Data</button>
    </div>
    <div class="data-container" v-if="healthData">
      <h3>{{ username }}'s Health Data:</h3>
      <div class="data">
        <p>Height: {{ healthData.height }} cm</p>
        <p>Weight: {{ healthData.weight }} kg</p>
        <p>BMI: {{ healthData.bmi }}</p>
        <p>Heart Rate: {{ healthData.heartRate }} bpm</p>
        <p>Blood Pressure: {{ healthData.bloodPressure }} mmHg</p>
      </div>
      <div class="input-container">
        <label for="score">Score:</label>
        <input type="number" id="score" v-model="score" />
        <button @click="saveHealthData">Save Health Data</button>
      </div>
    </div>
  </div>
</template>

<script>
import HealthMonitoringService from '@/HealthMonitoringService.js';

export default {
  name: 'HealthMonitoring',
  data() {
    return {
      username: '',
      healthData: null,
      score: null,
    };
  },
  methods: {
    getHealthData() {
      HealthMonitoringService.getHealthData(this.username)
        .then(data => {
          this.healthData = data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    saveHealthData() {
      HealthMonitoringService.saveHealthData(this.username, this.score)
        .then(data => {
          console.log(data);
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.health-monitoring {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
}

.header {
  text-align: center;
  margin-bottom: 20px;
}

.input-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.input-container label {
  font-weight: bold;
  margin-right: 10px;
}

.data {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.data p {
  margin: 0;
}

button {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 8px 12px;
  cursor: pointer;
}

button:hover {
  background-color: #0069d9;
}
</style>

