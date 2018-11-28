import React, {Component} from 'react';
import { Button} from 'reactstrap';
import {withRouter } from 'react-router-dom';

class Delete extends Component {
    constructor(props){
        super(props);
        this.handleDelete= this.handleDelete.bind(this);
    }

handleDelete(event){
        event.preventDefault();
        var stats = 0;
        fetch('/account/'+this.props.id,{
            method:'DELETE',
            credentials: "include"
        }).then(response =>{
            stats = response.ok;
        
        }).then(response => {
            if(stats === true){
                window.alert("Account deleted");
                window.location.reload();

            }else{window.alert("Account could not be deleted")}
        })
    }

  render() {
    return (
      <div>
        <Button color="danger" onClick = {(e) => {if(window.confirm("Are you sure want to delete this account?"))this.handleDelete(e)}}>Delete</Button>
      </div>
    );
  }
}
export default withRouter(Delete)