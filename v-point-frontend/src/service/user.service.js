import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:1997/api/test/';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'user', { headers: {Authorization: authHeader()} });
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'mod', { headers: {Authorization: authHeader()} });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'admin', { headers: {Authorization: authHeader()} });
    }
}

export default new UserService();