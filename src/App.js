import React, { Component } from 'react';
import Login from './Login';
import CreateUser from './CreateUser';
import Profile from './Profile';
import MainPage from './MainPage';

class App extends Component {
  render() {
    return (
      <div>
         <Profile/>
      </div>
    );
  }
}

export default App;
