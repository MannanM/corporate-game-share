import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

import { Row, Col, Grid } from 'react-bootstrap';
import Header from './nav/Header';
import PrivateRoute from './nav/PrivateRoute';
import Page from './nav/Page';

import GameList from './page/GameList';
import Home from './page/Home';

import { GetUser } from './Client';

class App extends Component {

 constructor(props) {
   super(props);
   this.state = { isAuthenticated: false };
 }

 componentDidMount() {
   //setTimeout(() =>
   GetUser().then(user => {
      console.log(user);
      this.setState({ isAuthenticated: true, user : user });
   }).catch(error => {
     console.log(error);
   });
   //, 2000);
 }

	render() {
	 const homePage = () => ( <Page title='Welcome' subtitle='to the Game-Share app!'><Home /></Page> );
	 const gamePage = () => ( <Page title='Games' subtitle='available to collect and share'><GameList /></Page> );
	 const userPage = () => ( <Page title='User' subtitle='User page'><GameList /></Page> );
		return (
    <Router>
      <>
        <Header user={this.state.user} />
        <Grid>
          <Row>
            <Col>
              <Route exact path='/' component={homePage} />
              <Route exact path='/game/list' component={gamePage} />
              <PrivateRoute exact path='/my-profile' component={userPage} authenticated={this.state.isAuthenticated} />
            </Col>
          </Row>
        </Grid>
      </>
    </Router>
		 )
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)