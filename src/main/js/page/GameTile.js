import React, { Component } from 'react';
import { Image, Col, Thumbnail, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

class GameTile extends Component {
  render() {
    return (
      <Col xs={6} sm={4} md={3} lg={2}>
      <LinkContainer to={`/game/${this.props.game.id}`}>
        <Thumbnail src={this.props.game.attributes.game.attributes.image} responsive="true" bsStyle="game-thumb">
          <h4>{this.props.game.attributes.game.attributes.name}</h4>
          <p className="thumbnail-game-cap">
           {this.props.game.attributes.game.attributes.developer} -&nbsp;
           {this.props.game.attributes.game.attributes.publisher}
          </p>
          <p>
            <Button bsStyle="primary">Borrow?</Button>
            &nbsp;
            <Button bsStyle="default">Star</Button>
          </p>
        </Thumbnail>
        </LinkContainer>
      </Col>
			 )
  }
}

export default GameTile;