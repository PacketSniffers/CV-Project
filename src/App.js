import React, { Component } from 'react';
import Login from './Login';
import CreateUser from './CreateUser';
import Profile from './Profile';
import MainPage from './MainPage';
import Privileges from './Privileges';
import EditAccount from './EditAccount';

class App extends Component {
  render() {
    return (
      <div>
         <Login/>
      </div>
    );
  }
}

export default App;
