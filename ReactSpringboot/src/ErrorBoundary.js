import React from 'react';
import {Input,Button, FormGroup} from 'reactstrap';
export default class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  componentDidCatch(error, info){
    this.setState({ hasError: true });
  }

  render() {
    if (this.state.hasError) {
      return(
      <FormGroup>
        <br/>
        <h6 for="exampleFile">Upload CV</h6>
        <Input type="file" name="file" id="exampleFile" />
        <br/>
        <Button color="primary">Upload</Button>
      </FormGroup>
    )
    }
    return this.props.children;
  }
}