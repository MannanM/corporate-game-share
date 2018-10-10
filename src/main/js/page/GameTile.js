import React, { Component } from 'react';
import { Image, Col, Thumbnail, Button } from 'react-bootstrap';

class GameTile extends Component {
  render() {
    return (
      <Col xs={6} sm={4} md={3} lg={2}>
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
      </Col>
			 )
  }
}

export default GameTile;