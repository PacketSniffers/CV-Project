import React, {Component} from 'react';
import {Container, Button, FormGroup,Input} from 'reactstrap';
import {withRouter } from 'react-router-dom';
import Linkify from 'react-linkify';

class LoginAlt extends Component {

  constructor(props){
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
    this.handleEmail = this.handleEmail.bind(this);
    this.handlePassword = this.handlePassword.bind(this);

    this.state = {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      _id: ''
    };
  }

  handleEmail(event){
    this.setState({email: event.target.value})
  }

  handlePassword(event){
    this.setState({password: event.target.value})
  }

  async onFormSubmit(event){
    event.preventDefault();
    let base64 = require('base-64');
    var stats = 0;
    await fetch('/account/mail/'+this.state.email,{
      method: 'POST',
      credentials: "include",
      headers:{
        'Authorization': 'Basic ' + base64.encode(this.state.email + ":" + this.state.password),
        'Accept':'application/json',
        'Content-Type': 'application/json',
      },
      
    }).then(response => {stats = response.ok; return response}).then(response => {
      
      if(stats === true){
        response.json().then(response => {
        if(response.userRole === 'admin'){
          this.props.history.push('/adminpage')
        }
        else{
          this.setState({firstName: response.firstName, lastName: response.lastName, _id: response._id})
          this.props.history.push({
            pathname:'/userprofile',
            search: '?id='+this.state._id
          })
        }})   
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
            <img width="100px" src={'http://www.fastrackerzkennel.com/wp-content/uploads/2014/03/male-placeholder-image.jpeg'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

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