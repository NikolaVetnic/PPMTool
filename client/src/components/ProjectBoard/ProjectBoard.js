import { connect } from "react-redux";
import { getBacklog } from "../../actions/backlogActions";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import PropTypes from "prop-types";
import React, { Component } from "react";

class ProjectBoard extends Component {
    componentDidMount() {
        const { id } = this.props.match.params; // extracting id from the route
        this.props.getBacklog(id);
    }

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

ProjectBoard.propTypes = {
    backlog: PropTypes.object.isRequired,
    getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
    backlog: state.backlog,
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
