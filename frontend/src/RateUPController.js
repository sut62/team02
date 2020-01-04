import axios from 'axios'

const SERVER_URL = 'http://localhost:9000';

const instance = axios.create({  
    baseURL: SERVER_URL,  
    timeout: 1000  
  });  

  export default {  
     
    getEdlevel: () => instance.get('eduLevels/edulevel',{
        transformResponse: (response) =>{
            return JSON.parse(response)
        }
    }),
    getStatus: () => instance.get('status/status',{
        transformResponse: (response) =>{
            return JSON.parse(response)
        }
    }),
    getAllRating: () => instance.get('ratings/rating', {
        transformResponse: (response) => {
            return JSON.parse(response)
        }
    }),
    findRateUPAll: () => instance.get('Rates/RateUsage',{
        transformResponse: (response) =>{
            return JSON.parse(response)
        }
    }),
    addRateUP: (data) => instance.post('Rates/add',{
        edulevel: data.edulevel,
        status: data.status,
        email: data.email,
        suggestion: data.suggestion,
        ratig: data.ratig
    })


  }