import ProjectTask from "./ProjectTasks/ProjectTask";
import React, { Component } from "react";

class Backlog extends Component {
    getTasksOfStatus(project_tasks, status) {
        return project_tasks
            .filter((project_task) => project_task.status === status)
            .map((project_task) => (
                <ProjectTask
                    key={project_task.id}
                    project_task={project_task}
                />
            ));
    }
    render() {
        const { project_tasks } = this.props;

        const tasksToDo = this.getTasksOfStatus(project_tasks, "TO_DO");
        const tasksInPr = this.getTasksOfStatus(project_tasks, "IN_PROGRESS");
        const tasksDone = this.getTasksOfStatus(project_tasks, "DONE");

        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-secondary text-white">
                                <h4>TO DO</h4>
                            </div>
                        </div>
                        {tasksToDo}
                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-primary text-white">
                                <h4>IN PROGRESS</h4>
                            </div>
                        </div>
                        {tasksInPr}
                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-success text-white">
                                <h4>DONE</h4>
                            </div>
                        </div>
                        {tasksDone}
                    </div>
                </div>
            </div>
        );
    }
}

export default Backlog;
