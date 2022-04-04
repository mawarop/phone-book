import axios from "axios";

const BASE_URL = "/api/v1/contacts"
const getConfig = () => {
    return {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
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
            return axios.get(BASE_URL + "?page=" + page + "&" + "input=" + input, getConfig())
        else return axios.get(BASE_URL + "?page=" + page, getConfig());
    }

    createContact(contactRequest) {
        return axios.post(BASE_URL, contactRequest, getConfig());

    }

    updateContact(id, contactRequest) {
        return axios.put(BASE_URL + '/' + id, contactRequest, getConfig())
    }

    deleteContact(id) {
        return axios.delete(BASE_URL + '/' + id, getConfig());
    }


}

export default new ContactService();