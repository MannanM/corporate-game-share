import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

import { Row, Col, Grid } from 'react-bootstrap';
import Header from './nav/Header';
import PrivateRoute from './nav/PrivateRoute';
import Page from './nav/Page';

import GameList from './page/GameList';
import Home from './page/Home';
import Privacy from './page/Privacy';
import UserProfile from './page/UserProfile';
import GameProfile from './page/GameProfile';

import { WhoAmI } from './Client';

class App extends Component {

 constructor(props) {
   super(props);
   this.state = { isAuthenticated: false };
 }

 componentDidMount() {
   WhoAmI().then(user => {
      console.log(user);
      this.setState({ isAuthenticated: true, user : user });
   }).catch(error => {
     console.log(error);
   });
 }

	render() {
		return (
    <Router>
      <>
        <Header user={this.state.user} />
        <Grid>
          <Row>
            <Col>
              <Route exact path='/' component={() => <Page title='Welcome' subtitle='to the Game-Share app!'><Home /></Page>} />
              <Route exact path='/browse/game' component={(props) =>
                 <Page title='Games' subtitle='available to collect and share'><GameList {...props} /></Page>} />
              <Route exact path='/game/:id' render={(props) => <GameProfile {...props} />} />
              <Route exact path='/user/:id' render={(props) => <UserProfile {...props} />} />
              <Route exact path='/privacy' component={() => <Page title='Privacy Policy' subtitle='Effective Date 2018-10-13'><Privacy /></Page>} />
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