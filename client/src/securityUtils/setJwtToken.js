import axios from "axios";

const setJwtToken = (token) => {
    if (token) {
        // setting the authorization key with value of token
        axios.defaults.headers.common["Authorization"] = token;
    } else {
        delete axios.defaults.headers.common["Authorization"];
    }
};

export default setJwtToken;
