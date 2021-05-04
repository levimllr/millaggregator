# Domain Model

See [the ER Diagram](https://lucid.app/lucidchart/invitations/accept/inv_cdc7bbcc-fe71-42cb-b780-3b899b4e0c80?viewport_loc=-77%2C4%2C1949%2C1467%2C0_0) for a graphic representation of the domain model.

## `user`

The user represents just that!

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `name` | `VARCHAR(255)` | User's name, obtained from sign-up form or auth provider. |
| `email` | `VARCHAR(255)` | User's email, obtained from sign-up form or auth provider. |
| `auth_provider` | `VARCHAR(255)` | OIDC authentication provider type (or self). One of: `SELF`, `GOOGLE`, `GITHUB`, etc. |
| `auth_provider_user_id` | `VARCHAR(255)` | User ID in system of authentication provider. |

## `user_auth_credential`

The username and password for a user who registers directly with the application instead of authentication with an OIDC provider.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `username` | `VARCHAR(255)` | User's username, obtained from sign-up form. |
| `password_hash` | `VARCHAR(255)` | User's hashed password. |

## `user_aggregator`

Join table between a user and an aggregator. A user can have many aggregators, and vice versa.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `user_id` | `INTEGER` | `user.id`, foreign key to `user` table. |
| `aggregator_id` | `INTEGER` | `aggregator.id`, foreign key to `aggregator` table. |
| `role` | `VARCHAR(255)` | Role of user in aggregator. One of: `OWNER`, `CONTRIBUTOR`, `VIEWER`. |

### `aggregator`

The container for a set of widgets, the application class itself.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `name` | `VARCHAR(255)` | Aggregator's name, obtained from user aggregation creation. |

### `widget`

Mini-applications within the aggregator that user can instantiate and arrange to their liking to craft their personal dashboard.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `widget_type` | `VARCHAR(255)` | Type of widget. One of: `ALGORHYTHM`, `GITHUB`, `MONICA`, `YNAB`. |
| `monica_interface_id` | `INTEGER` | `monica_interface.id`, foreign key to `monica_interface` table. May be null in case of non-`MONICA`-type widget. |
| `github_interface_id` | `INTEGER` | `github_interface.id`, foreign key to `github_interface` table. May be null in case of non-`GITHUB`-type widget. |
| `ynab_interface_id` | `INTEGER` | `ynab_interface.id`, foreign key to `ynab_interface` table. May be null in case of non-`YNAB`-type widget. |
| `aggregator_id` | `INTEGER` | `aggregator.id`, foreign key to `aggregator` table. An aggregator can have many widgets. |

### `algorhythm`

Table containing an algorhythm (flow-chart to-do list) with definition, state, and configuration. An Algorhythm widget can have many algorhythms.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `name` | `VARCHAR(255)` | Algorhythms's name, obtained from algorhythm creation. |
| `algorhythm_type` | `VARCHAR(255)` | Type of algorhythm. One of: `FLOWCHART`, `SEQUENTIAL_STEP`, `RANDOM_STEP` |
| `widget_id` | `INTEGER` | `widget.id`, foreign key to `widget` table. |
| `description` | `TEXT` | Description of algorithm, syntax may consist of `flowchart.js` DSL, Markdown ordered list, Markdown unordered list. |
| `current_step` | `VARCHAR(255)` | The name of the current step of the algorhythm based on the user's interactions. The name must be in the description. |
| `active_window_id` | `VARCHAR(255)` | `active_window.id`, foreign key to `active_window` table. |

### `github_interface`

WIP. Table containing state, keys, and configuration for authorized GitHub account for use with the Task widget.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `repo_name` | `VARCHAR(255)` | Name of the GitHub repository used to contain the project, issues, labels, milestones, etc. |
| `access_token` | `VARCHAR(255)` | Encrypted OAuth access token for GitHub account. |
| `refresh_token` | `VARCHAR(255)` | Encrypted OAuth refresh token for GitHub account. |

### `github_project`

WIP. Table containing state and configuration for a Task widget's GitHub repository project used to organize tasks (as issues in a project board with labels and milestones).

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `github_interface_id` | `INTEGER` |  `github_interface.id`, foreign key to `github_interface` table. |
| `active_window_id` | `INTEGER` |  `active_window.id`, foreign key to `active_window` table. |
| `priority_label` | `VARCHAR(255)` | The issue label to prioritize in displaying (besides the bang priority labels). |

### `active_window`

Table containing data for weekly windows of time to default to for display of certain widgets or widget features.

| property name | data type | description |
| --- | --- | --- |
| `id` | `INTEGER` | Primary key. |
| `start_time` | `TIME` | Describes the start time. |
| `end_time` | `TIME` | Describes the end time. |
| `sundday` | `TINYINT` | Whether or not this window exists during Sunday. |
| `monday` | `TINYINT` | Whether or not this window exists during Monday. |
| `tuesday` | `TINYINT` | Whether or not this window exists during Tuesday. |
| `wednesday` | `TINYINT` | Whether or not this window exists during Wednesday. |
| `thursday` | `TINYINT` | Whether or not this window exists during Thursday. |
| `friday` | `TINYINT` | Whether or not this window exists during Friday. |
| `saturday` | `TINYINT` | Whether or not this window exists during Saturday. |

### `monica_interface`

WIP. Big question: how to generalize widget API so that new widgets can be added most easily? Will probably find out when implementing these first three.

### `ynab_interface`

WIP. DITTO.
