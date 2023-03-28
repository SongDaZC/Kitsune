<template>
  <div id="app">
    <h1>Health Monitoring</h1>
    <div>
      <label for="username">Username:</label>
      <input v-model="username" id="username" type="text" />
      <button @click="addUser">Add User</button>
    </div>
    <div>
      <label for="score">Score:</label>
      <input v-model.number="score" id="score" type="number" />
      <button @click="addScore">Add Score</button>
    </div>
    <div>
      <h2>Scores</h2>
      <ul>
        <li v-for="score in scores" :key="score">{{ score }}</li>
      </ul>
    </div>
  </div>
      <h3>Enter your health data:</h3>
      <div>
        <label for="usual-signs">Usual signs (e.g. temperature, blood pressure)</label>
        <div class="signs">
          <div v-for="(sign, index) in usualSigns" :key="index">
            <input type="number" v-model.number="usualSigns[index]">
          </div>
        </div>
      </div>
      <div>
        <label for="unusual-signs">Unusual signs (e.g. headache, nausea)</label>
        <div class="signs">
          <div v-for="(sign, index) in unusualSigns" :key="index">
            <input type="number" v-model.number="unusualSigns[index]">
          </div>
        </div>
      </div>
      <div>
        <label for="sleep-time">Sleep time (in hours)</label>
        <input type="number" v-model.number="sleepTime">
      </div>
      <div>
        <label for="tcm-observation">TCM observation (e.g. tongue, pulse)</label>
        <textarea v-model="tcmObservation"></textarea>
      </div>
      <button @click="saveHealthData">Save Health Data</button>
</template>


<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      username: "",
      score: 0,
      usualSigns: Array.from({ length: 10 }, () => null),
      unusualSigns: Array.from({ length: 10 }, () => null),
      scores: [],
    };
  },
  methods: {
    async addUser() {
      try {
        await axios.post(
          "http://localhost:8080/health/user/" + this.username
        );
        this.username = "";
      } catch (error) {
        console.error("Error adding user:", error);
      }
    },
    async addScore() {
      try {
        const data = {
          usualSigns: this.usualSigns,
          unusualSigns: this.unusualSigns,
          sleepTime: 0,
          tcmObservation: "",
        };
        await axios.post(
          "http://localhost:8080/health/user/" + this.username + "/score",
          data
        );
        this.getScores();
      } catch (error) {
        console.error("Error adding score:", error);
      }
    },
    async getScores() {
      try {
        const response = await axios.get(
          "http://localhost:8080/health/user/" + this.username + "/scores"
        );
        this.scores = response.data;
      } catch (error) {
        console.error("Error getting scores:", error);
      }
    },
  },
};
</script>


<style>
body {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

