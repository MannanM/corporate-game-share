import React, { Component } from 'react';
import { FormGroup, ControlLabel, Row, Col } from 'react-bootstrap';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';
import { Link } from 'react-router-dom'

import { GetGames } from "../Client";
import ConsoleSelect from "../element/ConsoleSelect";
import AddButton from "../element/AddButton";

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
     dataField: 'data.attributes.name', text: 'Name', sort: true,
        formatter: (cellContent, row) => (
        <Link to={{ pathname: `/game/${row.data.id}`, state: {game: row} }}>
          {row.data.attributes.name}
        </Link>
     )
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
   }, {
     dataField: 'df1',
     isDummyField: true,
     text: '',
     formatter: (cellContent, row) => ( <AddButton gameId={row.data.id} bsSize='normal' /> )
   }];
 }

	render() {
	 const { SearchBar } = Search;

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