import React, { Component } from 'react';
import { PageHeader, Image, Col, Thumbnail,
  Button, ButtonGroup, Panel, ListGroup, ListGroupItem } from 'react-bootstrap';

import AddButton from '../element/AddButton.js'
import { GetGame } from '../Client';

class GameProfile extends Component {
  componentDidMount() {
    if (this.props.location.state) {
      this.setState({game: this.props.location.state.game});
    } else {
      console.log(this);
      GetGame(this.props.match.params.id).then((data) => {
         this.setState({ game: data });
      });
    }
  }

  render() {
    let attr = {};
    let gameId = -1;
    if (this.state && this.state.game) {
       attr = this.state.game.data.attributes;
       gameId = this.state.game.data.id;
    }
    const { name, developer, publisher, console,
      exclusive, releaseDate, genres, image } = attr;

    return (
      <>
        <PageHeader>
          &nbsp;
          {name} <small>{developer}</small>
        </PageHeader>
        <Col sm={12} md={9}>
         <Panel>
           <Panel.Heading>
             <Panel.Title>Game Details</Panel.Title>
           </Panel.Heading>
           <Panel.Body>
             <Image src={image} thumbnail />
           </Panel.Body>
           <ListGroup>
             <ListGroupItem><b>Release Date:</b> {releaseDate}</ListGroupItem>
             <ListGroupItem><b>Console</b>: {console}</ListGroupItem>
             <ListGroupItem><b>Developer</b>: {developer}</ListGroupItem>
             <ListGroupItem><b>Publisher</b>: {publisher}</ListGroupItem>
             <ListGroupItem><b>Console Exclusive</b>: {exclusive ? 'Yes' : 'No'}</ListGroupItem>
             <ListGroupItem><b>Genres</b>: {genres}</ListGroupItem>
           </ListGroup>
         </Panel>
        </Col>
        <Col sm={12} md={3}>
         <Panel>
           <Panel.Heading>
             <Panel.Title>Actions</Panel.Title>
           </Panel.Heading>

           <Panel.Body>
           <ButtonGroup vertical block>
             <AddButton gameId={gameId} />
             <Button>Add to Wishlist</Button>
           </ButtonGroup>
           </Panel.Body>
         </Panel>
        </Col>
      </>
			 )
  }
}

export default GameProfile;