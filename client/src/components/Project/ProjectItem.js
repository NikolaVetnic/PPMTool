import { Link } from "react-router-dom";
import React, { Component } from "react";

class ProjectItem extends Component {
    render() {
        const { project } = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-2">
                            <span className="mx-auto">
                                {project.projectIdentifier}
                            </span>
                        </div>
                        <div className="col-lg-6 col-md-4 col-8">
                            <h3>{project.projectName}</h3>
                            <p>{project.description}</p>
                        </div>
                        <div className="col-md-4 d-none d-lg-block">
                            <ul className="list-group">
                                <a href="#">
                                    <li className="list-group-item board">
                                        <i className="fa fa-flag-checkered pr-1">
                                            <span className="pr-caption">
                                                &nbsp;&nbsp;Project Board{" "}
                                            </span>
                                        </i>
                                    </li>
                                </a>
                                <Link
                                    to={`/updateProject/${project.projectIdentifier}`}
                                >
                                    <li className="list-group-item update">
                                        <i className="fa fa-edit pr-1">
                                            <span className="pr-caption">
                                                &nbsp;&nbsp;Update Project Info
                                            </span>
                                        </i>
                                    </li>
                                </Link>
                                <a href="">
                                    <li className="list-group-item delete">
                                        <i className="fa fa-minus-circle pr-1">
                                            <span className="pr-caption">
                                                &nbsp;&nbsp;Delete Project
                                            </span>
                                        </i>
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ProjectItem;
