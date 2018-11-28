import React, {Component} from 'react';
import {Button} from 'reactstrap';

export default class Download extends Component {
    constructor(props){
        super(props);
        this.handleDownload = this.handleDownload.bind(this);
    }

handleDownload(event){
        event.preventDefault();
        
        fetch('/account/download/'+this.props.id)
        .then(response => {
            const filename =  response.headers.get('Content-Disposition').split('filename=')[1];

            response.blob().then(blob => {
            let url = window.URL.createObjectURL(blob);
            let a = document.createElement('a');
            a.href = url;
            a.download = filename;
            a.click();
        });
        });
    }


  render() {
    return (
      <div>
        <Button color="primary" style={{marginRight: '10px', float: 'left'}} onClick = {this.handleDownload}>Download</Button>
      </div>
    );
  }
}


