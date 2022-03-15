import { combineReducers } from "redux";
import backlogReducer from "./backlogReducer";
import errorReducer from "./errorReducer";
import projectReducer from "./projectReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
    backlog: backlogReducer,
    errors: errorReducer,
    project: projectReducer,
    security: securityReducer,
});
