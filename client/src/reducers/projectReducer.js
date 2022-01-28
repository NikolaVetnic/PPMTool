import { DELETE_PROJECT, GET_PROJECT } from "../actions/types";
import { GET_PROJECTS } from "../actions/types";

const initialState = {
    projects: [],
    project: {},
};

// eslint-disable-next-line import/no-anonymous-default-export
export default function (state = initialState, action) {
    switch (action.type) {
        case DELETE_PROJECT:
            return {
                ...state,
                projects: state.projects.filter(
                    (project) => project.projectIdentifier !== action.payload
                ),
            };

        case GET_PROJECTS:
            return {
                ...state,
                projects: action.payload,
            };

        case GET_PROJECT:
            return {
                ...state,
                project: action.payload,
            };

        default:
            return state;
    }
}
