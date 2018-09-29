import React, { Component } from 'react';
import { PageHeader } from 'react-bootstrap';

class Page extends Component{
  render() {
  return (
  <>
		  <PageHeader>
      {this.props.title} <small>{this.props.subtitle}</small>
    </PageHeader>
			   {this.props.children}
			 </>
			 )
  }
}

export default Page;