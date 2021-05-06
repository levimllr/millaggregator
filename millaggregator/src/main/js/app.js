const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {aggregators: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/aggregators'}).done(response => {
			this.setState({aggregators: response.entity._embedded.aggregators});
		});
	}

	render() {
		return (
			<AggregatorList aggregators={this.state.aggregators}/>
		)
	}
}

class AggregatorList extends React.Component{
	render() {
		const aggregators = this.props.aggregators.map(aggregator =>
			<Aggregator key={aggregator._links.self.href} aggregator={aggregator}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
					</tr>
					{aggregators}
				</tbody>
			</table>
		)
	}
}

class Aggregator extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.aggregator.name}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
