import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav, Container, Input, Table, Button, ButtonGroup, Media, Col, Row, Form, FormGroup, Label, FormText} from 'reactstrap';
import Linkify from 'react-linkify';
import {withRouter } from 'react-router-dom';
import queryString from 'query-string';

class EditAccount extends Component {

    constructor(props){
        super(props);
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
        this.handleLastNameChange = this.handleLastNameChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        

        this.state = {
            firstName:'',
            lastName: '',
            email: '',
            password: 'default'
        };
    }

    handleFirstNameChange(event){
    this.setState({firstName: event.target.value});
  }
    handleLastNameChange(event){
    this.setState({lastName: event.target.value});
  }
    handleEmailChange(event){
    this.setState({email: event.target.value});
  }
  
  

  onFormSubmit(event) {
    var pathname = window.location.pathname.split('/')
    event.preventDefault();
    fetch('/account/'+pathname[2],{
      method: 'PUT',
      headers:{
        'Accept' : 'application/json',
        'Content-Type': 'application/json' 
      },
      body: JSON.stringify(this.state),
    }).then(() =>{this.props.history.push('/')});

    }

  render() {
    return (
    <div>
        <Navbar color="light" light expand="md">
            <NavbarBrand style={{marginLeft: '10px'}} class="logo" href="#">CV Management</NavbarBrand>
            <Nav className="ml-auto" navbar>
                <NavItem>
                    <NavLink onClick={(e) => this.props.history.goBack()}>Profile</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="/logout">Logout</NavLink>
                </NavItem>
            </Nav>
        </Navbar>

      <Container>
        <center>
          <form onSubmit={this.onFormSubmit} class='loginFormNav'>
            <img width="100px" src={'http://www.fastrackerzkennel.com/wp-content/uploads/2014/03/male-placeholder-image.jpeg'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Edit Account</h1>
            </FormGroup>
          
            <FormGroup>
              <Input autoFocus type="email" name="email" id="exampleEmail" value = {this.state.email} onChange = {this.handleEmailChange} placeholder="Email" />
            </FormGroup>

            <FormGroup>
              <Input type="text" name="forename" id="exampleEmail" value = {this.state.firstName} onChange = {this.handleFirstNameChange} placeholder="Forename" />
            </FormGroup>

            <FormGroup>
              <Input type="text" name="surname" id="exampleEmail" value = {this.state.lastName} onChange={this.handleLastNameChange} placeholder="Surname" />
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Edit Account</Button>
            </FormGroup>

          </form>
        </center>
      </Container>
      </div>
    );
  }
}
export default withRouter(EditAccount);