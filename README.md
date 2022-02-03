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

## Very important branches

-   `pt0031` : Create Redux Store
-   `pt0032` : Create Project from React
-   `pt0033` : Create validation errors from Redux
-   `pt0052` : Get Project Backlog, the happy path (optimisation via @JsonIgnore)
-   `pt0054` : Handle ProjectNotFoundException (handling exceptions summary)
-   `pt0062` : Types and Reducers for ProjectTasks (setting up types and reducers summary)

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
