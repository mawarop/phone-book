import axios from "axios";

class AuthService {

    login(username, password) {
        return axios.post("login", {"username": username, "password": password});
    }
}

export default new AuthService();