import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import axios from "axios";
import jwt_decode from "jwt-decode";
import setJwtToken from "../securityUtils/setJwtToken";

export const createNewUser = (newUser, history) => async (dispatch) => {
    try {
        await axios.post("/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {},
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data,
        });
    }
};

export const login = (LoginRequest) => async (dispatch) => {
    try {
        // hit the endpoint with post action and pass it the login request
        const res = await axios.post("/api/users/login", LoginRequest);

        // extract the token from the res.data
        const { token } = res.data;

        // store the token in the local storage
        localStorage.setItem("jwtToken", token);

        // set our token in the header (set authorization in the request)
        setJwtToken(token);

        // decode the token on React
        const decoded = jwt_decode(token);

        // dispatch to our securityReducer
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded,
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data,
        });
    }

    // extract the token from the res.data
    // store the token in the local storage
    // set our token in the header (set authorization in the request)
    // decode the token on React
    // dispatch to our securityReducer
};
