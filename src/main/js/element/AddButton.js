import React, { Component } from 'react';
import { Button } from 'react-bootstrap';

import { AddToLibrary } from '../Client';

class AddButton extends Component {
  constructor(props, context) {
    super(props, context);

    this.handleClick = this.handleClick.bind(this);

    this.state = { message: 'Add to Collection', isLoading: false };
  }

  handleClick() {
    this.setState({ isLoading: true });

    AddToLibrary(this.props.gameId).then(() => {
       this.setState({ message: 'Successfully added!', isLoading: true });
    }).catch(error => {
       console.log(error);
       if (error.status == 400) {
         this.setState({ message: 'You already have this game', isLoading: true });
       } else if (error.status == 401) {
         this.setState({ message: 'Please login first', isLoading: true });
       } else {
         this.setState({ message: error.body.message, isLoading: true });
       }
    });
  }

 render() {
   const { isLoading, message } = this.state;
   const bsSize = this.props.bsSize ? this.props.bsSize : 'large';
   return <Button bsStyle="primary" bsSize={bsSize} disabled={isLoading}
               onClick={!isLoading ? this.handleClick : null} >
             {message}
          </Button>
 }
}

export default AddButton;