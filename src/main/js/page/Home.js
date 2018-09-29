import React, { Component } from 'react';
import { Jumbotron, Button } from 'react-bootstrap';

class Home extends Component{
  render() {
  return (
  <>

<Jumbotron>
  <h1>Share your game collection!</h1>
  <p>
    Add the consoles & games you own, rate and share with others.
  </p>
  <p>
    <Button bsStyle="primary">Learn more...</Button>
    &nbsp;
    <Button bsStyle="success" href="/login/facebook">Login</Button>
  </p>
</Jumbotron>


			 </>
			 )
  }
}

export default Home;