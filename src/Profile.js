import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, Table, ButtonGroup, Media, TabContent, TabPane, Nav, Card, Button, CardTitle, CardText, FormGroup, Label} from 'reactstrap';
import Linkify from 'react-linkify';
import classnames from 'classnames';
import GoogleMapReact from 'google-map-react'
import PDFComponent from './PDFComponent'

const AnyReactComponent = ({ text }) => (
  <div style={{
    color: 'white', 
    background: '#4286f4',
    padding: '15px 10px',
    display: 'inline-flex',
    textAlign: 'center',
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: '100%',
    transform: 'translate(-50%, -50%)'
  }}>
    {text}
  </div>
);

export default class Profile extends Component {
    static defaultProps = {
        center: { lat: 53.474192, lng: -2.286306 },
        zoom: 11
    }

    constructor(props) {
        super(props);

        this.toggle = this.toggle.bind(this);
        this.state = {
            activeTab: '1'
        };
    }

    toggle(tab) {
        if (this.state.activeTab !== tab) {
            this.setState({
                activeTab: tab
            });
        }
    }

    render() {
        const {pageNumber, numPages} = this.state
        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand style={{marginLeft: '10px'}} class="logo" href="#">CV Management</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <NavLink href="#">Profile</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#">Logout</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>

                <center>
                    <h1 style={{marginTop: '40px'}}>Profile</h1>
                </center>

                <Container name="mainPage" style={{backgroundColor: 'white', minHeight: '700px', marginTop: '30px', boxShadow: '5px 5px #c9cacc'}}>  
                    <Row>
                        <Col xs="6">
                            <Row>
                                <Col xs="3" xs="6">
                                    <img width="250px" src={'https://bit.ly/2Fvw9xi'} style={{paddingTop: '50px', paddingLeft: '50px'}} className="img-responsive"/>
                                </Col>
                                <Col xs="3" xs="6">
                                    <h6 style={{paddingTop: '50px', color: '#888a8c'}}>Name</h6>
                                    <div style={{width: '200px', height: '1px', backgroundColor: 'grey'}}></div>
                                    <h5 style={{paddingTop: '10px', color: '#636466'}}>Aharan Manoharan</h5>
                                    <h6 style={{paddingTop: '10px', color: '#888a8c'}}>Email</h6>
                                    <div style={{width: '200px', height: '1px', backgroundColor: 'grey'}}></div>
                                    <h5 style={{paddingTop: '10px', color: '#636466'}}><a href="mailto:testmail@gmail.com">testmail@gmail.com</a></h5>
                                </Col>
                            </Row>
                            <center>
                                <div style={{height: '350px', width: '82%', paddingTop: '30px'}}>
                                    <GoogleMapReact
                                        bootstrapURLKeys={{ key: 'AIzaSyAboCB2WdY0vusOcitH6N2Aho9c39YtObY'}}
                                        defaultCenter={this.props.center}
                                        defaultZoom={this.props.zoom}
                                    >
                                    <AnyReactComponent
                                        lat={53.474192}
                                        lng={-2.286306}
                                        text={'QA Consulting'}
                                    />
                                    </GoogleMapReact>
                                </div>
                            </center>
                            <br/>
                            <Linkify><a style={{paddingLeft: '50px'}} href='#'>Edit Account</a></Linkify>
                        </Col>
                        <Col xs="6">
                            <Nav tabs style={{marginTop: '10px'}}>
                                <NavItem>
                                    <NavLink className={classnames({ active: this.state.activeTab === '1' })} onClick={() => { this.toggle('1'); }}>
                                        CV 1
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className={classnames({ active: this.state.activeTab === '2' })} onClick={() => { this.toggle('2'); }}>
                                        CV 2
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className={classnames({ active: this.state.activeTab === '3' })} onClick={() => { this.toggle('3'); }}>
                                        CV 3
                                    </NavLink>
                                </NavItem>
                            </Nav>
                            <TabContent activeTab={this.state.activeTab}>
                                <TabPane tabId="1">
                                    <Row>
                                        <Col sm="12">
                                            <PDFComponent/>
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane tabId="2">
                                    <Row>
                                    <Col sm="12">
                                         <FormGroup>
                                            <br/>
                                            <h6 for="exampleFile">Upload CV</h6>
                                            <Input type="file" name="file" id="exampleFile" />
                                            <br/>
                                            <Button color="primary">Upload</Button>
                                        </FormGroup>
                                    </Col>
                                    </Row>
                                </TabPane>
                                <TabPane tabId="3">
                                    <Row>
                                        <Col sm="12">
                                            <FormGroup>
                                                <br/>
                                                <h6 for="exampleFile">Upload CV</h6>
                                                <Input type="file" name="file" id="exampleFile" />
                                                <br/>
                                                <Button color="primary">Upload</Button>
                                            </FormGroup>
                                        </Col>
                                    </Row>
                                </TabPane>
                            </TabContent>
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}