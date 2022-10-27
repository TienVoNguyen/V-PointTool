import axios from "axios"
import authHeader from "@/service/auth-header";
export class UserService {
    static serverURL = `http://localhost:8080`

    static createUser(user){
        let userForm = new FormData(user)
        let dataURL = `${this.serverURL}/register`
        return axios.post(dataURL, userForm, {headers: authHeader()})
    }

    static getAll(params) {
        let dataURL = `${this.serverURL}/list`
        return axios.get(dataURL, {params, headers: authHeader()});
    }

    static deleteUser(userId){
        let dataURL = `${this.serverURL}/delete/${userId}`
        return axios.delete(dataURL, { headers: authHeader() })
    }

    static findById(userId){
        let dataURL = `${this.serverURL}/findByIdUser/${userId}`
        return axios.get(dataURL, { headers: authHeader() })
    }

    static getVpoint(userId) {
        let dataURL = `${this.serverURL}/api/mark/myVpoint/${userId}`
        return axios.get(dataURL, {headers: authHeader()});
    }

    static getVpointByYear(userId, params) {
        let dataURL = `${this.serverURL}/api/mark/myVpointByYear/${userId}`
        return axios.get(dataURL, {params, headers: authHeader()});
    }

    static getYear(userId) {
        let dataURL = `${this.serverURL}/api/mark/getYear/${userId}`
        return axios.get(dataURL, {headers: authHeader()});
    }

    static getVpointByTime(userId, params) {
        let dataURL = `${this.serverURL}/api/mark/${userId}`
        return axios.get(dataURL, {params, headers: authHeader()});
    }

    static getUserById(id) {
        let dataURL = `${this.serverURL}/user/${id}`;
        return axios.get(dataURL, {headers: authHeader()});
    }

}