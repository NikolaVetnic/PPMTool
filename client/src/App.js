import React, { Component } from "react";
import AddProject from "./components/Project/AddProject";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <Route exact path="/dashboard" component={Dashboard} />
                <Route exact path="/addProject" component={AddProject} />
            </div>
        </Router>
    );
}

export default App;
