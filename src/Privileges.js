import React, {Component} from 'react';
import { Media, Container, Col, Row, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Linkify from 'react-linkify';

export default class Privileges extends Component {
  render() {
    return (
      <Container>
        <center>
          <form method='post' action='#' class='loginForm'>
            <img width="100px" src={'http://www.fastrackerzkennel.com/wp-content/uploads/2014/03/male-placeholder-image.jpeg'} style={{marginBottom: '30px', borderRadius: '200px'}} className="img-responsive"/>

            <FormGroup>
              <h1 class="display-5">Set Privileges</h1>
            </FormGroup>
          
            <FormGroup>
              <Input autoFocus type="email" name="email" id="exampleEmail" placeholder="Email" />
            </FormGroup>

            <FormGroup>
                <Input type="select" name="users" id="setPrivileges">
                    <option>Trainee</option>
                    <option>Admin</option>
                    <option>Recruiter</option>
                </Input>
            </FormGroup>

            <FormGroup>
              <Button color="primary" size="me" block>Set Privileges</Button>
            </FormGroup>
          </form>
        </center>
      </Container>
    );
  }
}