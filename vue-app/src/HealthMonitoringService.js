import axios from 'axios';

const apiClient = axios.create({
  baseURL: '/Kitsune_project/rest/health',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default {
  getHealthData(username) {
    return apiClient.get(`/user/${username}`)
      .then(response => response.data)
      .catch(error => {
        console.log(error);
        throw new Error('Unable to retrieve health data');
      });
  },

  saveHealthData(username, data) {
    return apiClient.post(`/user/${username}/score`, data)
      .then(response => response.data)
      .catch(error => {
        console.log(error);
        throw new Error('Unable to save health data');
      });
  }
};

