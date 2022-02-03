import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import React, { Component } from "react";

class ProjectBoard extends Component {
    render() {
        const { id } = this.props.match.params;
        return (
            <div className="container">
                <Link
                    to={`/addProjectTask/${id}`}
                    className="btn btn-primary mb-3"
                >
                    <i className="fas fa-plus-circle">
                        <span className="pr-caption">
                            &nbsp;&nbsp;Create Project Task
                        </span>
                    </i>
                </Link>
                <br />
                <hr />
                {/* <!-- Backlog STARTS HERE --> */}
                <Backlog />
                {/* <!-- Backlog ENDS HERE --> */}
            </div>
        );
    }
}

export default ProjectBoard;
