import React, {Component} from 'react';
import { Media, Container, Col, Row, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Linkify from 'react-linkify';

export default class EditAccount extends Component {
  render() {
    return (
      <Container>
        <center>
          <form method='post' action='#' class='loginForm'>
            <img width="100px" src={'https://avatars0.githubusercontent.com/u/36368080?s=400&v=4'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Edit Account</h1>
            </FormGroup>
          
            <FormGroup>
              <Input autoFocus type="email" name="email" id="exampleEmail" placeholder="Email" />
            </FormGroup>

            <FormGroup>
              <Input type="text" name="forename" id="exampleEmail" placeholder="Forename" />
            </FormGroup>

            <FormGroup>
              <Input type="text" name="surname" id="exampleEmail" placeholder="Surname" />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" placeholder="Current Password" />
            </FormGroup>

            <FormGroup>
              <Input type="password" name="password" id="examplePassword" placeholder="New Password" />
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Edit Account</Button>
            </FormGroup>
          </form>
        </center>
      </Container>
    );
  }
}