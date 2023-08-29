import axios from 'axios';

// base URL for API-Endpoints
export default(url='http://localhost:8000/api/v1') => {
    return axios.create({
        baseURL : url
    });
};