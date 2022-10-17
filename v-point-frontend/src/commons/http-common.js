import axios from "axios";

export const jsonRequest = axios.create({
    baseURL: "http://localhost:1997/api",
    headers: {
        "Content-type": 'application/json',
        processData: false
    }
});

export default axios.create({
    baseURL: "http://localhost:1997/api",
    headers: {
        enctype:"multipart/form-data",
        "Content-type": false,
        processData: false
    }
});