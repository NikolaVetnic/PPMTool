import { BrowserRouter as Router, Route } from "react-router-dom";
import { Provider } from "react-redux";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AddProject from "./components/Project/AddProject";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import React, { Component } from "react";
import store from "./store";

function App() {
    return (
        <Provider store={store}>
            <Router>
                <div className="App">
                    <Header />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route exact path="/addProject" component={AddProject} />
                </div>
            </Router>
        </Provider>
    );
}

export default App;
