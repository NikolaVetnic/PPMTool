import React, { Component } from "react";

class BacklogColumn extends Component {
    render() {
        const { className, tasks, title } = this.props;
        return (
            <div className="col-md-4">
                <div className="card text-center mb-2">
                    <div className={className}>
                        <h4>{title}</h4>
                    </div>
                </div>
                {tasks}
            </div>
        );
    }
}

export default BacklogColumn;
