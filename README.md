# Read Me

## Sections

1. Introduction
2. Spring Back-End. Basic CRUD operations. Project entity : `pt0007 - pt0018`
3. React & Redux Front-End. Project entity CRUD operations : `pt0023 - pt0045`
4. Spring Back-End. Add ProjectTasks : `pt0046 - pt0059`
5. React & Redux Front-End. Add PRojectTasks : `pt0062 - `

## Stop points

-   20220125 1500 : next up pt20, the latest branch `pt0018`, `master` up to date
-   20220126 0041 : next up pt32, the latest branch `pt0031`, `master` up to date
-   20220126 1028 : next up pt37, the latest branch `pt0036`, `master` up to date
-   20220128 0000 : next up pt43, the latest branch `pt0042`, `master` up to date
-   20220128 0900 : next up pt46, the latest branch `pt0045`, `master` up to date
-   20220130 1455 : next up pt52, the latest branch `pt0050`, `master` up to date
-   20220203 0042 : next up pt68, the latest branch `pt0067`, `master` up to date
-   20220203 0949 : next up pt72, the latest branch `pt0071`, `master` up to date

## Very important branches

-   `pt0031` : Create Redux Store
-   `pt0032` : Create Project from React
-   `pt0033` : Create validation errors from Redux
-   `pt0052` : Get Project Backlog, the happy path (optimisation via @JsonIgnore)
-   `pt0054` : Handle ProjectNotFoundException (handling exceptions summary)
-   `pt0062` : Types and Reducers for ProjectTasks (setting up types and reducers summary)
-   `pt0071` : Organize ProjectTasks by status and priority (add my own BacklogColumn component)
-   `pt0072` : ProjectBoard algorithm (tampering with URL and returning errors in case there are any)

## Quick Reference

### Load ProjectTasks into state

* set up the action - backlogActions.js :
   

    export const getBacklog = (backlog_id) => async (dispatch) => { ... }
* go to the component that uses the action - ProjectBoard.js :
   

    import { connect } from "react-redux";
    import { getBacklog } from "../../actions/backlogActions";
    import PropTypes from "prop-types";
    ...
    constructor()
    compDidMount() {
        const {id} = this.props.match.params;
        this.props.getBacklog(id);
    }
    ...
    ComponentName.propTypes = {
        neededObject: PropTypes.object.isRequired,
        neededFunc: PropTypes.func.isRequired
    }
    mapStateToProps = state => ({
        whatIsNeeded: state.whatIsNeeded,
        errors: state.errors
    })
    export default connect(null, {action})(ComponentName);

### Display ProjectTasks on the ProjectBoard

* pass the ProjectTasks to the Backlog component as props :


    render() {
        const { id } = this.props.match.params;
        const { project_tasks } = this.props.backlog;
        return (
            ...
            <Backlog project_tasks={project_tasks} />
            ...
        );
    }
* extract the ProjectTasks from props in Backlog component and map to ProjectTask component:


    render() {
        const { project_tasks } = this.props;
        const tasks = project_tasks.map((project_task) => (
            <ProjectTask 
                key={project_task.id} 
                project_task={project_task} 
            />
        ));
        ...
    }

### UpdateProjectTask Component

* create React Class Component and copy the HTML into `render()` method
* create a route for the new component in App.js (note that parameters in the path MUST MATCH the names on the Spring back-end, i.e. if method parameters are `backlog_id` and `pt_id` the names on the React front-end must be exactly the same)
* set up the 'View / Update' link on the ProjectTask component (parameters needed are already in the loaded ProjectTask)
* wire up backlog actions - function `getProjectTask(backlog_id, pt_id, history)`
* get all the imports into UpdateProjectTask component and connect them properly - `{ connect }`, `{ getProjectTask }`, `classnames`, `PropTypes`
* set up `componentDidMount()` lifecycle hook - VERY IMPORTANT to get params like this `const { backlog_id, pt_id } = this.props.match.params;`
* set up a constructor - call `super()`, load blank attributes (make sure names in the form input fields match)
* set up `componentWillReceiveProps()` hook - destructure attributes from the `props.project_task` and put those into state
* set up input field values - `value={this.state.attributeName}`
* set up `onChange(e)` method
* set up `onSubmit(e)` method
* in backlogActions.js create an action to update ProjectTask 
* in UpdateProjectTask at the bottom add `updateProjectTask` action to `connect()` method call and to `PropTypes`
* in UpdateProjectTask add errors to the state and PropTypes (also at the bottom)
* add `updateProjectTask()` call to `onSubmit()` method
* add functionality to 'Back to Project Board' button
* set up the classnames on the input fields in the UpdateProjectTask component

