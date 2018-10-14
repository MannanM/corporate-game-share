import React, { Component } from 'react';
import { FormControl } from 'react-bootstrap';

import { GetConsoles } from '../Client';

class ConsoleSelect extends Component {
  componentDidMount() {
    GetConsoles().then((result) => {
      this.setState({ consoles: result.data });
    });
  }

 render() {
   let options = <></>;
   if (this.state && this.state.consoles) {
      options = this.state.consoles.map((c) =>
         <option key={c.id} value={c.attributes.shortName}>
            {c.attributes.name}
         </option>);
   }
   return <FormControl componentClass="select" placeholder="select" {...this.props} >
             <option value="">Please select...</option>
             {options}
          </FormControl>
 }
}

export default ConsoleSelect;