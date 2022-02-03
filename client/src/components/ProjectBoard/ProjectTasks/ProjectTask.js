import { connect } from "react-redux";
import { deleteProjectTask } from "../../../actions/backlogActions";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import React, { Component } from "react";

class ProjectTask extends Component {
    getPriorityStyleObject(priority) {
        return priority === 3
            ? {
                  priorityString: "LOW",
                  priorityClass: "bg-info text-light",
              }
            : priority === 2
            ? {
                  priorityString: "MEDIUM",
                  priorityClass: "bg-warning text-light",
              }
            : {
                  priorityString: "HIGH",
                  priorityClass: "bg-danger text-light",
              };
    }

    formatDate(date) {
        return date
            .toISOString()
            .slice(0, 19)
            .replace(/-/g, "/")
            .replace("T", " ");
    }

    onDeleteClick(backlog_id, pt_id) {
        this.props.deleteProjectTask(backlog_id, pt_id);
    }

    render() {
        const { project_task } = this.props;

        const priorityStyle = this.getPriorityStyleObject(
            project_task.priority
        );

        const dueDateString = "" + project_task.dueDate;
        const dateDue =
            "Date due : " +
            (dueDateString === "null"
                ? "none"
                : project_task.dueDate.substring(0, 11));

        return (
            <div className="card mb-1 bg-light">
                <div
                    className={`card-header text-primary ${priorityStyle.priorityClass}`}
                >
                    ID: {project_task.projectSequence} -- Priority :{" "}
                    {priorityStyle.priorityString}
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{project_task.summary}</h5>
                    <p className="card-text text-truncate ">
                        {project_task.acceptanceCriteria}
                    </p>
                    <p className="card-text text-truncate ">{dateDue}</p>
                    <Link
                        to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`}
                        className="btn btn-primary"
                    >
                        View / Update
                    </Link>

                    <button
                        className="btn btn-danger ml-4"
                        onClick={this.onDeleteClick.bind(
                            this,
                            project_task.projectIdentifier,
                            project_task.projectSequence
                        )}
                    >
                        Delete
                    </button>
                </div>
            </div>
        );
    }
}

ProjectTask.propTypes = {
    deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
