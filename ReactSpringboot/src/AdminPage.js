import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import Download from './Download.js';
import Delete from './Delete.js';
export default class MainPage extends Component {
    static defaultPropers = {
    }
    emptyState = {
     firstName:'',
      lastName: '',
    };
    constructor(props) {
        super(props);
        this.state = {
            searchTerm: "",
            users: []
        }

        this.search = (event) => this.setState ({
            searchTerm: event.target.value
        })
    }


    async componentDidMount() {
        var stats;
        const group = await (await fetch(`/account/getall`)
        .then(response => {
            stats = response.ok; return response;})).json();
        if (stats === true){
            this.setState({users: group});
        } else {
            this.props.history.push('/');
        }
        
    }
    searchTable = () => {
        //let users = ["Dan Higgins", "Paul Higgins", "Aharan Manoharan", "Abz Haider", "Brad Hudson-Grant"]
        let users = this.state.users; 
        users = users.sort();


        var test = []
        let pos = 0;
        let input = this.state.searchTerm
        let test2 = this.state.dropdownOpen
        let toggle = this.toggle

        users.forEach(function(arrayItem,arrayIndex,array){
            if ((array[arrayIndex].firstName + ' ' + array[arrayIndex].lastName).toLowerCase().match(input.toLowerCase())){
            pos++
            test.push(
                
                <tr>
                    <th scope="row">{pos}</th>
                    <td>{array[arrayIndex].firstName + ' ' + array[arrayIndex].lastName}</td>
                    <td>
                        <ButtonGroup size="sm">
                            <Button outline color="danger">CV 1</Button>
                            <Button outline color="success" disabled>CV 2</Button>
                            <Button outline color="primary" disabled>CV 3</Button>
                        </ButtonGroup>
                    </td>
                    {/* <td><Button onClick = {this.handleDownload} color="primary" size="sm">Download</Button></td> */}
                    <td>{array[arrayIndex].file!==null?
                        <Download id={array[arrayIndex]._id}/>:
                        <Button outline color="primary" disabled>Download</Button>}
                        
                        </td>
                    <td><Button color="info" size="sm" disabled>Flag</Button></td>
                    <td><Button color="warning" size="sm" disabled>Favourite</Button></td>
                    {/* <td><Button color="danger" size="sm">Delete</Button></td> */}
                    <td><Delete id={array[arrayIndex]._id}/></td>
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
                            <NavLink href="/privileges">Edit/Set Permissions</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/logout">Logout</NavLink>
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