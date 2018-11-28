import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav, Container, Input, Table, Button, ButtonGroup, Media, Col, Row, Form, FormGroup, Label, FormText} from 'reactstrap';
import Linkify from 'react-linkify';
import {withRouter } from 'react-router-dom';

class EditPassword extends Component {

    constructor(props){
        super(props);
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.onOldPasswordChange = this.onOldPasswordChange.bind(this);
        this.onNewPasswordChange = this.onNewPasswordChange.bind(this);

        this.state={
            oldPassword:'',
            newPassword:''
        }
    }

    onFormSubmit(event){
        var pathname = window.location.pathname.split('/')
        event.preventDefault();
        fetch('/account/password/'+pathname[2],{
            method: 'PUT',
            credentials: 'include',
            headers:{
                'Accept':'application/json',
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(this.state),
        }).then(() =>{this.props.history.push('/');});
        

    }
    onOldPasswordChange(event){
        this.setState({oldPassword: event.target.value})
    }

    onNewPasswordChange(event){
        this.setState({newPassword: event.target.value})
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
              <h1 class="display-5">Change Password</h1>
            </FormGroup>

            <FormGroup>
              <Input autoFocus type="password" name="password" id="examplePassword" value = {this.state.oldPassword} onChange = {this.onOldPasswordChange} required pattern = "(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,}" title = "Password must contain one uppercase, one lowercase and one number character" placeholder="Current Password" />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" value={this.state.newPassword} onChange={this.onNewPasswordChange} required pattern = "(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,}" title = "Password must contain one uppercase, one lowercase and one number character" placeholder="New Password" />
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
export default withRouter(EditPassword)