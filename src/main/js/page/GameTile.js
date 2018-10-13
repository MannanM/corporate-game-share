import React, { Component } from 'react';
import { Image, Col, Thumbnail, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

class GameTile extends Component {
  render() {
    console.log(this.props.game);
    return (
      <Col xs={6} sm={4} md={3} lg={2}>
        <div className="thumbnail">
          <LinkContainer to={`/game/${this.props.game.attributes.game.id}`}>
              <Image src={this.props.game.attributes.game.attributes.image} responsive="true" bsStyle="game-thumb" />
          </LinkContainer>
          <div className='caption'>
               <LinkContainer to={`/game/${this.props.game.attributes.game.id}`}>
             <h4>
                 {this.props.game.attributes.game.attributes.name}
             </h4>
               </LinkContainer>
             <p className="thumbnail-game-cap">
              {this.props.game.attributes.game.attributes.developer} -&nbsp;
              {this.props.game.attributes.game.attributes.publisher}
             </p>
             <p>
               <Button bsStyle="primary">Borrow?</Button>
               &nbsp;
               <Button bsStyle="default">Star</Button>
             </p>
          </div>
        </div>
      </Col>
			 )
  }
}

export default GameTile;