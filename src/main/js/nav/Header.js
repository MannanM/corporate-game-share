import React, { Component } from 'react';
import { Nav, Navbar, NavItem, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

export default class Header extends Component {
 render() {
		return (
		  <Navbar inverse collapseOnSelect>
      <Navbar.Header>
        <Navbar.Brand>
          <a href='#'>Game-Share</a>
        </Navbar.Brand>
        <Navbar.Toggle />
      </Navbar.Header>
      <Navbar.Collapse>
        <Nav>
          <LinkContainer to='/' exact={true}>
            <NavItem eventKey={1}>
              Home
            </NavItem>
          </LinkContainer>

          <LinkContainer to='/game/list'>
            <NavItem eventKey={2}>
              Game List
            </NavItem>
          </LinkContainer>
        </Nav>
        <Navbar.Text pullRight>
          { this.props.user ? 'Signed in as: ' + this.props.user.userAuthentication.details.name : '' }
        </Navbar.Text>
      </Navbar.Collapse>
    </Navbar>
		 )
	 }
}
