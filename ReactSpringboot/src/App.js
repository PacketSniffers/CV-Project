import React, { Component } from 'react';
import Login from './Login';
import LoginAlt from './LoginAlt';
import CreateUser from './CreateUser';
import {Router, Route, Switch} from 'react-router';

class App extends Component {
  render() {
    return (
      <div>
         <LoginAlt />
      </div>
    );
  }
}

export default App;
