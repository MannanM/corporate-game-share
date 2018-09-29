import React, { Component } from 'react';
import { FormControl } from 'react-bootstrap';

class ConsoleSelect extends Component {
 render() {
   return <FormControl componentClass="select" placeholder="select"
        {...this.props} >
     <option value="">Please select...</option>
     <option value="PS4">Playstation 4</option>
     <option value="X1">Xbox 1</option>
   </FormControl>
 }
}

export default ConsoleSelect;