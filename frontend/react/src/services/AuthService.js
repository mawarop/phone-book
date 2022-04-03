import axiosInstance from "./axiosInstance";

class AuthService {

    login(username, password) {
        return axiosInstance.post("login", {"username": username, "password": password});
    }
}

export default new AuthService();