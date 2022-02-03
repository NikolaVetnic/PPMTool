import ProjectTask from "./ProjectTasks/ProjectTask";
import React, { Component } from "react";

class Backlog extends Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-secondary text-white">
                                <h4>TO DO</h4>
                            </div>
                        </div>

                        {/* <!-- SAMPLE PROJECT TASK STARTS HERE --> */}
                        <ProjectTask />
                        {/* <!-- SAMPLE PROJECT TASK ENDS HERE --> */}
                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-primary text-white">
                                <h4>IN PROGRESS</h4>
                            </div>
                        </div>
                        {/* <!-- SAMPLE PROJECT TASK STARTS HERE --> */}

                        {/* <!-- SAMPLE PROJECT TASK ENDS HERE --> */}
                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-success text-white">
                                <h4>DONE</h4>
                            </div>
                        </div>
                        {/* <!-- SAMPLE PROJECT TASK STARTS HERE --> */}

                        {/* <!-- SAMPLE PROJECT TASK ENDS HERE --> */}
                    </div>
                </div>
            </div>
        );
    }
}

export default Backlog;
