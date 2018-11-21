import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav, Col, Row, Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import Linkify from 'react-linkify';

import ReactTable from "react-table";
import 'react-table/react-table.css'

export default class MainPage extends Component {
    static defaultPropers = {
    }
    
    constructor() {
        super();
        this.state = {
            searchTerm: "",
        }

        this.search = (event) => this.setState ({
            searchTerm: event.target.value
        })
    }

    searchTable = () => {
        let users = ["Dan Higgins", "Paul Higgins", "Aharan Manoharan", "Abz Haider", "Brad Hudson-Grant"]
        users = users.sort();


        let test = []
        let pos = 0;
        let input = this.state.searchTerm
        let test2 = this.state.dropdownOpen
        let toggle = this.toggle

        users.forEach(function(arrayItem,arrayIndex,array){
            if (array[arrayIndex].toLowerCase().match(input.toLowerCase())){
            pos++
            test.push(
                <tr>
                    <th scope="row">{pos}</th>
                    <td>{array[arrayIndex]}</td>
                    <td>
                        <ButtonGroup size="sm">
                            <Button outline color="danger">CV 1</Button>
                            <Button outline color="success">CV 2</Button>
                            <Button outline color="primary">CV 3</Button>
                        </ButtonGroup>
                    </td>
                    <td><Button color="primary" size="sm">Download</Button></td>
                    <td><Button color="info" size="sm">Flag</Button></td>
                    <td><Button color="warning" size="sm">Favourite</Button></td>
                    <td><Button color="danger" size="sm">Delete</Button></td>
                </tr>
            );
            }
        })
        return test;
    }

    render() {
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand style={{marginLeft: '10px'}} class="logo" href="#">CV Management</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <NavLink href="#">Profile</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#">Logout</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>

                <center>
                    <h1 style={{marginTop: '40px'}}>Search</h1>
                    <Input onInput={this.search} style={{width: '800px'}} autoFocus type="text" name="search" id="searchBox" placeholder="Search for name" />
                </center>

                <Container name="mainPage" style={{backgroundColor: 'white', minHeight: '600px', marginTop: '50px', boxShadow: '5px 5px #c9cacc'}}>
                    <Table responsive>   
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Filename</th>
                                <th>Download</th>
                                <th>Flag</th>
                                <th>Favourite</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.searchTable()}
                        </tbody>
                    </Table>   
                </Container>
            </div>
        );
    }
}