import React, {Component} from 'react';
import { Media, Container, Col, Row, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Linkify from 'react-linkify';

export default class CreateUser extends Component {
  render() {
    return(
       <Container>
        <center>
          <form method='post' action='#' class='createForm'>
            <img width="100px" src={'https://bit.ly/2Fvw9xi'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Create User</h1>
            </FormGroup>
            
            <FormGroup>
              <Input autoFocus type="text" name="forename" id="exampleEmail" placeholder="Forename" />
            </FormGroup>

            <FormGroup>
              <Input type="text" name="surname" id="exampleEmail" placeholder="Surname" />
            </FormGroup>
          
            <FormGroup>
              <Input type="email" name="email" id="exampleEmail" placeholder="Email" />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" placeholder="Password" />
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Create User</Button>
            </FormGroup>
          </form>
        </center>
      </Container>
    )
  }
}