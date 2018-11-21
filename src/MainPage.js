import React, {Component} from 'react';
import { Navbar, NavbarBrand, NavItem, NavLink, Nav, Col, Row, Container, Input, Table, Button } from 'reactstrap';
import Linkify from 'react-linkify';

export default class MainPage extends Component {
    static defaultPropers = {
    }
    
    constructor() {
        super();
        this.state = {
            searchTerm: ""
        }

        this.changeColour = (event) => this.setState ({
            searchTerm: event.target.value
        })
    }

    searchTable = () => {
        let user = ["Dan Higgins", "Paul Higgins", "Aharan Manoharan", "Abz Haider"]

        let table = []
        let test = []
        let input = this.state.searchTerm

        for (let i = 0; i < user.length; i++) {
            table.push(
                <tr>
                    <th scope="row">{i+1}</th>
                    <td>{user[i]}</td>
                    <td>cv1.pdf</td>
                    <td><Button color="primary" size="sm">Download</Button></td>
                    <td><Button color="info" size="sm">Flag</Button></td>
                    <td><Button color="warning" size="sm">Favourite</Button></td>
                    <td><Button color="danger" size="sm">Delete</Button></td>
                </tr>
            );
        }

          user.forEach(function(arrayItem,arrayIndex,array){
              if(array[arrayIndex].match(input)){
                alert(array[arrayIndex])
                test.push(
                    <tr>
                        <th scope="row">{arrayIndex+1}</th>
                        <td>{array[arrayIndex]}</td>
                        <td>cv1.pdf</td>
                        <td><Button color="primary" size="sm">Download</Button></td>
                        <td><Button color="info" size="sm">Flag</Button></td>
                        <td><Button color="warning" size="sm">Favourite</Button></td>
                        <td><Button color="danger" size="sm">Delete</Button></td>
                    </tr>
                );
                return test
              }
        })

        // for (let count = 0; count < user.length; count++) {
        //     if (input == user[count]) {
        //         test.push(
        //             <tr>
        //                 <th scope="row">{count+1}</th>
        //                 <td>{input}</td>
        //                 <td>cv1.pdf</td>
        //                 <td><Button color="primary" size="sm">Download</Button></td>
        //                 <td><Button color="info" size="sm">Flag</Button></td>
        //                 <td><Button color="warning" size="sm">Favourite</Button></td>
        //                 <td><Button color="danger" size="sm">Delete</Button></td>
        //             </tr>
        //         );
        //         return test
        //     }
        // }
        return table
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
                    <Input onInput={this.changeColour} style={{width: '800px'}} autoFocus type="text" name="search" id="searchBox" placeholder="Search for name" />
                </center>

                <Container style={{backgroundColor: 'white', height: '600px', marginTop: '50px'}}>
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