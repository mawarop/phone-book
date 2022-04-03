import axios from "axios";

const BASE_URL = "/api/v1/contacts"
const tokeConfig = () => {
    return {
        headers: {
            Authorization: sessionStorage.getItem("jwtToken")
        }
    }
}

class ContactService {
    // getContacts(page){
    //     return axios.get(BASE_URL + "?page=" + page)
    // }

    getContacts(page, input) {
        if (input)
            return axios.get(BASE_URL + "?page=" + page + "&" + "input=" + input, tokeConfig())
        else return axios.get(BASE_URL + "?page=" + page, tokeConfig());
    }

    updateContact(id, contactRequest) {
        return axios.put(BASE_URL + '/' + id, contactRequest, tokeConfig())
    }

    deleteContact(id) {
        return axios.delete(BASE_URL + '/' + id, tokeConfig());
    }


}

export default new ContactService();