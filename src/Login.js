import React, {Component} from 'react';
import { Media, Container, Col, Row, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Linkify from 'react-linkify';

export default class Login extends Component {
  render() {
    return (
      <Container>
        <center>
          <form method='post' action='#' class='loginForm'>
            <img width="100px" src={'https://avatars0.githubusercontent.com/u/36368080?s=400&v=4'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Sign In</h1>
            </FormGroup>
          
            <FormGroup>
              <Input autoFocus type="email" name="email" id="exampleEmail" placeholder="Email" />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" placeholder="Password" />
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Login</Button>
            </FormGroup>

            <FormGroup>
              <Linkify><a style={{float: 'left'}} href='#'>Create account</a></Linkify>
              <Linkify><a style={{float: 'right'}} href='#'>Forgot password</a></Linkify>
            </FormGroup>
          </form>
        </center>
      </Container>
    );
  }
}