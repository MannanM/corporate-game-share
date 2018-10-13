import React, { Component } from 'react';
import { FormGroup, ControlLabel, Row, Col } from 'react-bootstrap';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';

import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';

import { GetGames } from "../Client";
import ConsoleSelect  from "../ConsoleSelect";

class GameList extends Component {
 constructor(props, context) {
   super(props, context);

   this.handleChange = this.handleChange.bind(this);

   this.state = {
     console: '',
     games: []
   };
 }

 handleChange(e) {
    this.setState({ console: e.target.value });
    GetGames(e.target.value).then(games => {
       this.setState({ games : games.data });
    });
 }

 genreFormatter(cell, row) {
   const spans = row.data.attributes.genres ?
      row.data.attributes.genres.map(function(name, id) {
        const key = row.data.id + '-' + id;
        return <span key={key} className="genre label label-success">{name}</span>;
      }) : <span>N/A</span>
   return (
     <h5>{spans}</h5>
   );
 }

 columns() {
   return [{
     dataField: 'data.attributes.name', text: 'Name', sort: true
   }, {
     dataField: 'data.attributes.developer', text: 'Developer', sort: true
   }, {
     dataField: 'data.attributes.publisher', text: 'Publisher', sort: true
   }, {
     dataField: 'data.attributes.releaseDate', text: 'Release', sort: true
   }, {
     dataField: 'data.attributes.genres',
     text: 'Genres',
     formatter: this.genreFormatter
   }];
 }

	render() {
	 const { SearchBar } = Search;
	 const rowEvents = {
    onClick: (e, row, rowIndex) => {
      const location = {
        pathname: '/game/' + row.data.id,
        state: { game: row }
      };
      this.props.history.push(location);
    }
  };

		return (
    <ToolkitProvider keyField='data.id' data={ this.state.games } columns={ this.columns() }
       defaultSearch={[{ dataField: 'data.id', order: 'desc' }]} search>
      { props => (
       <>
         <Row>
         <FormGroup controlId="formControlsSelect">
         <Col xs={12} md={6}>
           <ControlLabel>Select Console</ControlLabel>
           <ConsoleSelect value={this.state.console} onChange={this.handleChange} />
           </Col>
           <Col xs={12} sm={6}>
           <ControlLabel>Search</ControlLabel>
           <SearchBar { ...props.searchProps } />
           </Col>
         </FormGroup>
         </Row>
         <br />
         <BootstrapTable pagination={ paginationFactory() }
           rowEvents={ rowEvents }
           noDataIndication="Table is Empty"
           striped hover condensed
           { ...props.baseProps }
         />
       </>
    ) }
   </ToolkitProvider>
		)
	}
}

export default GameList;