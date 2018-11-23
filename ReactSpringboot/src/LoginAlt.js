import React, {Component} from 'react';
import { Media, Container, Col, Row, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import { Link, withRouter } from 'react-router-dom';
import Linkify from 'react-linkify';
import axios from 'axios';
import createHistory from 'history/createBrowserHistory';

class LoginAlt extends Component {

  constructor(props){
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
    this.handleEmail = this.handleEmail.bind(this);
    this.handlePassword = this.handlePassword.bind(this);

    this.state = {
      email: '',
      password: ''
    };
  }

  handleEmail(event){
    this.setState({email: event.target.value})
  }

  handlePassword(event){
    this.setState({password: event.target.value})
  }

  onFormSubmit(event){
    event.preventDefault();
    let base64 = require('base-64');
    var stats = 0;
    fetch('/account/mail/'+this.state.email,{
      method: 'POST',
      headers:{
        'Authorization': 'Basic ' + base64.encode(this.state.email + ":" + this.state.password),
        'Accept':'application/json',
        'Content-Type': 'application/json',
      },
      
    }).then(response => {stats = response.ok; return response.json()}).then(response => {
      
      if(stats == true){
        if(response.userRole === 'admin'){
          this.props.history.push('/adminpage')
        }
        else{
          this.props.history.push('/placeholder')
        }   
      }
        else{
          alert("Incorrect login")
        }
    });
    
  }

  render() {
    return (
      <Container>
        <center>
          <form onSubmit = {this.onFormSubmit} class='loginForm'>
            <img width="100px" src={'https://avatars0.githubusercontent.com/u/36368080?s=400&v=4'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 className="display-5">Sign In</h1>
            </FormGroup>
          
            <FormGroup>
              <Input autoFocus type="email" name="email" id="exampleEmail" placeholder="Email" onChange = {this.handleEmail} required />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" placeholder="Password" onChange = {this.handlePassword} required />
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Login</Button>
            </FormGroup>

            <FormGroup>
              <Linkify><a style={{float: 'left'}} href='/createuser'>Create account</a></Linkify>
              <Linkify><a style={{float: 'right'}} href='#'>Forgot password</a></Linkify>
            </FormGroup>
          </form>
        </center>
      </Container>
    );
  }
}
export default withRouter(LoginAlt)