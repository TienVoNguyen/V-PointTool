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

    static getVpoint(userId) {
        let dataURL = `${this.serverURL}/api/mark/myVpoint/${userId}`
        return axios.get(dataURL, {headers: authHeader()});
    }

}
