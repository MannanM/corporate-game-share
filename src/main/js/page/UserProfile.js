import React, { Component } from 'react';
import { PageHeader, Image, Col, Thumbnail, Button } from 'react-bootstrap';

import { GetLibrary, GetUser } from '../Client';
import GameTile from './GameTile';

class UserProfile extends Component {
  componentDidMount() {
    const { id } = this.props.match.params;
    GetLibrary(id).then(library => {
      this.setState({ library : library });
    }).catch(error => {
      console.log(error);
    });
    GetUser(id).then(user => {
      this.setState({ user: user });
    }).catch(error => {
      console.log(error);
    });
  }

  render() {
    let imageLink = '';
    let name = 'Loading...';
    let games = [];
    if (this.state) {
      if (this.state.user) {
       name = this.state.user.data.attributes.name;
       imageLink = this.state.user.data.attributes.imageLink;
      }
      if (this.state.library) {
       games = this.state.library.data || [];
      }
    }

    const listItems = games.map((g) => <GameTile key={g.id} game={g} />);
    return (
      <>
        <PageHeader>
          <Image src={imageLink} thumbnail />
          &nbsp;
          {name} <small>Level 1</small>
        </PageHeader>
        <h3>Games</h3>
        {listItems}
      </>
			 )
  }
}

export default UserProfile;