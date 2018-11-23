import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav, Col, Row, Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import Linkify from 'react-linkify';


export default class Logout extends Component {
    static defaultPropers = {
    }
    async componentDidMount() {
        const group =  (await fetch(`/account/logout`).then(this.props.history.push('/')));
    }
    
    render() {
        return (
            <div>
                Logout successful!
            </div>
        );
    }
}