import React, { Component } from "react";

import { Nav, Navbar, NavDropdown, NavItem, MenuItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";

export default class Header extends Component {
 constructor(props) {
  super(props);
 }

 render() {
		return (
		  <Navbar inverse collapseOnSelect>
      <Navbar.Header>
        <Navbar.Brand>
          <a href="#">Game-Share</a>
        </Navbar.Brand>
        <Navbar.Toggle />
      </Navbar.Header>
      <Navbar.Collapse>
        <Nav>
        <LinkContainer to="/" exact={true}>
          <NavItem eventKey={1}>
            Home
          </NavItem>
          </LinkContainer>

        <LinkContainer to="/game/list">
          <NavItem eventKey={2}>
            Game List
          </NavItem>
          </LinkContainer>
          <NavDropdown eventKey={3} title="Dropdown" id="basic-nav-dropdown">
            <MenuItem eventKey={3.1}>Action</MenuItem>
            <MenuItem eventKey={3.2}>Another action</MenuItem>
            <MenuItem eventKey={3.3}>Something else here</MenuItem>
            <MenuItem divider />
            <MenuItem eventKey={3.3}>Separated link</MenuItem>
          </NavDropdown>
        </Nav>
        <Nav pullRight>
          <NavItem eventKey={1} href="#">
            Link Right
          </NavItem>
          <NavItem eventKey={2} href="#">
            Link Right
          </NavItem>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
		 )
	 }
}
