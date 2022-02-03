import ProjectTask from "./ProjectTasks/ProjectTask";
import React, { Component } from "react";
import BacklogColumn from "./BacklogColumn";

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
                    <BacklogColumn
                        tasks={tasksToDo}
                        title="TO DO"
                        className="card-header bg-secondary text-white"
                    />
                    <BacklogColumn
                        tasks={tasksInPr}
                        title="IN PROGRESS"
                        className="card-header bg-primary text-white"
                    />
                    <BacklogColumn
                        tasks={tasksDone}
                        title="DONE"
                        className="card-header bg-success text-white"
                    />
                </div>
            </div>
        );
    }
}

export default Backlog;
