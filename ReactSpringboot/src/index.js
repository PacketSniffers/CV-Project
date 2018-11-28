import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import './bootstrap.min.css';
import App from './App';
import CreateUser from './CreateUser';
import AdminPage from './AdminPage';
import Logout from './Logout';
import Profile from './Profile';
import EditAccount from './EditAccount';
import Privileges from './Privileges';
import EditPassword from './EditPassword';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

ReactDOM.render(
    <BrowserRouter>
    <Switch>
        <Route exact path ="/" component={App} />
        <Route path = "/createuser" component={CreateUser}/>
        <Route path = "/adminpage" component={AdminPage} />
        <Route path = "/logout" component={Logout}/>
        <Route path = "/userprofile" component={Profile} />
        <Route path = "/editaccount/:id" component={EditAccount} />
        <Route path = "/privileges" component={Privileges} />
        <Route path = "/editpassword/:id" component={EditPassword} />
    </Switch>
    </BrowserRouter>,
 document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
