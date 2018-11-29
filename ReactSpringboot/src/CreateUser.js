import React, {Component} from 'react';
import { Container, Button, FormGroup, Input } from 'reactstrap';
import {withRouter } from 'react-router-dom';
import Linkify from 'react-linkify';

class CreateUser extends Component {

  constructor(props){
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
    this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
    this.handleLastNameChange = this.handleLastNameChange.bind(this);
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
 
    this.state = {
     firstName:'',
      lastName: '',
      email: '',
      password:'',
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
    handlePasswordChange(event){
    this.setState({password: event.target.value});
  }
  
  
    onFormSubmit(event) {
    event.preventDefault();
    fetch('/account/',{
      method: 'POST',
      headers:{
        'Accept' : 'application/json',
        'Content-Type': 'application/json' 
      },
      body: JSON.stringify(this.state),
    });
    this.props.history.push('/')

    }

  
  render() {
    return(
       <Container>
        <center>
          <form onSubmit={this.onFormSubmit} class = 'createForm'> 
            <img width="100px" src={'http://www.fastrackerzkennel.com/wp-content/uploads/2014/03/male-placeholder-image.jpeg'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Create User</h1>
            </FormGroup>
            
            <FormGroup>
              <Input autoFocus type="text" name="firstName" id="examplefirstname" value = {this.state.firstName} placeholder="Forename" onChange ={this.handleFirstNameChange} required minlength = "1" maxlength = "20"/>
            </FormGroup>

            <FormGroup>
              <Input type="text" name="lastName" id="examplelastname" value = {this.state.lastName} placeholder="Surname" onChange ={this.handleLastNameChange} required minlength = "1" maxlength = "20"/>
            </FormGroup>
          
            <FormGroup>
              <Input type="email" name="email" id="exampleEmail"  value = {this.state.email} placeholder="Email" onChange ={this.handleEmailChange} required/>
            </FormGroup>

            <FormGroup style={{marginBottom: '-8px'}}>
              <Input type="password" name="password" id="examplePassword"  value = {this.state.password} placeholder="Password" 
              onChange ={this.handlePasswordChange} required pattern = "(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,}" title = "Password must contain one uppercase, one lowercase and one number character"/>​
            </FormGroup>

            <FormGroup>
              <Button type="submit" value="Create User" color="primary" size="me" block > Create User </Button>
            </FormGroup>

            <FormGroup>
              <Linkify><a style={{float: 'left'}} href='/'>Log in</a></Linkify>
            </FormGroup>
          </form>
        </center>
      </Container>
    )
  }
}
export default withRouter(CreateUser)