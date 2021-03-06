import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AddProject from "./components/Project/AddProject";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import Login from "./components/UserManagement/Login";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import React, { Component } from "react";
import Register from "./components/UserManagement/Register";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";

function App() {
    return (
        <Provider store={store}>
            <Router>
                <div className="App">
                    <Header />
                    {
                        // Public Routes
                    }
                    <Route exact path="/" component={Landing} />
                    <Route exact path="/register" component={Register} />
                    <Route exact path="/login" component={Login} />
                    {
                        // Private Routes
                    }
                    <Route exact path="/addProject" component={AddProject} />
                    <Route
                        exact
                        path="/addProjectTask/:id"
                        component={AddProjectTask}
                    />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route
                        exact
                        path="/updateProject/:id"
                        component={UpdateProject}
                    />
                    <Route
                        exact
                        path="/updateProjectTask/:backlog_id/:pt_id"
                        component={UpdateProjectTask}
                    />
                    <Route
                        exact
                        path="/projectBoard/:id"
                        component={ProjectBoard}
                    />
                </div>
            </Router>
        </Provider>
    );
}

export default App;
