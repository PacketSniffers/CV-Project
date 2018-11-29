import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Linkify from 'react-linkify';
import classnames from 'classnames';
import GoogleMapReact from 'google-map-react'
import PDFComponent from './PDFComponent'
import queryString from 'query-string';
import ErrorBoundary from './ErrorBoundary.js';
import Download from './Download.js';

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
        this.onFileUpload = this.onFileUpload.bind(this);
        this.handleFileInsert =this.handleFileInsert.bind(this);

        this.toggle = this.toggle.bind(this);
        this.state = {
            activeTab: '1',
            _id : '',
            nameWithoutSpace:'',
            name:'',
            email:'',
            file:'',
            hasFile:false
        };
    }

    toggle(tab) {
        if (this.state.activeTab !== tab) {
            this.setState({
                activeTab: tab
            });
        }
    }

    async onFileUpload(event){
        if(!this.state.file) {
            this.setState({error: 'Please upload a file.'})
            return;
        }
        event.preventDefault();
        var stats = 0;
        let data = new FormData();
        data.append('file', this.state.file)
        data.append('name', this.state.file.name)
        await fetch('/account/upload/'+this.state._id,{
        method: 'POST',
        credentials: "include",
        // headers:{
        //     'Content-Type': 'multipart/form-data',
        // },
        body:data
        });
        window.location.reload();
    }

    onFileDelete(event){
        event.preventDefault();
        var stats = 0;
        fetch('/account/deletecv/'+this.state._id,{
            method: 'DELETE',
            credentials: "include"
        }).then(response =>{
            stats = response.ok;
        
        }).then(response => {
            if(stats === true){
                window.alert("CV deleted");
                window.location.reload();

            }else{window.alert("CV could not be deleted")}
        });
    }

    handleFileInsert(event){
        this.setState({file:event.target.files[0]})
        console.log(this.state.file.name)
    }

    


    async componentDidMount(){
        var queryParams = await queryString.parse(this.props.location.search);
        await this.setState({
            _id: queryParams.id,
        });
        var stats = 0;
        var fileIn;
        await fetch('/account/'+this.state._id,{
            method: 'GET',
            credentials: "include",
            headers: {
                'Accept':'application/json',
                'Content-Type': 'application/json',
            },  
        })
        .then(response => {stats = response.ok; return response.json()}).then(response => {
            var temp = false;
            if (response.file !== null){
                temp = true;
            }
            if(stats === true){
                this.setState({name:response.firstName + ' ' + response.lastName, email:response.email,nameWithoutSpace:response.firstName + response.lastName, hasFile: temp}) 
            }else{
                alert("Incorrect login")
                this.props.history.push('/logout')
                }
            })
    //          .then( fetch('/account/retrieve/'+this.state._id,{
    //         method: 'GET',
    //         credentials: "include",
    //         headers: {
    //             responseType: 'blob'
    //         },  
    //     }).then(response => {return response.data}).then(response => {return new Blob([response],{type: 'application/pdf'});}).then(fileIn => this.setState({
    //         file: URL.createObjectURL(fileIn).toString()
    //     })));
        
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
                            <NavLink href="/logout">Logout</NavLink>
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
                                    <img width="250px" src={'http://www.fastrackerzkennel.com/wp-content/uploads/2014/03/male-placeholder-image.jpeg'} style={{paddingTop: '50px', paddingLeft: '50px'}} className="img-responsive"/>
                                </Col>
                                <Col xs="3" xs="6">
                                    <h6 style={{paddingTop: '50px', color: '#888a8c'}}>Name</h6>
                                    <div style={{width: '200px', height: '1px', backgroundColor: 'grey'}}></div>
                                    <h5 style={{paddingTop: '10px', color: '#636466', wordWrap: 'break-word'}}>{this.state.name}</h5>
                                    <h6 style={{paddingTop: '10px', color: '#888a8c'}}>Email</h6>
                                    <div style={{width: '200px', height: '1px', backgroundColor: 'grey'}}></div>
                                    <h5 style={{paddingTop: '10px', color: '#636466', wordWrap: 'break-word'}}><a href="mailto:testmail@gmail.com">{this.state.email}</a></h5>
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
                            <Linkify><a style={{paddingLeft: '50px'}} href={'/editaccount/'+this.state._id}>Edit Account</a>
                            <a style={{paddingLeft: '50px'}} href={'/editpassword/'+this.state._id}>Edit Password</a></Linkify>
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
                                            {/* <PDFComponent filename={this.state.file.substring(5, this.state.file.length).trim()}/> */}
                                            <FormGroup>
                                            <br/>
                                            <h6 for="exampleFile">Upload CV</h6>
                                            <Input type="file" name="file" id="exampleFile" onChange={this.handleFileInsert}/>
                                            <br/>
                                            {this.state.file !== ''?
                                            <Button onClick={this.onFileUpload} style={{marginRight: '10px', float: 'left'}} color="info">Upload</Button>:
                                            <Button onClick={this.onFileUpload} style={{marginRight: '10px', float: 'left'}} color="info" disabled>Upload</Button>}
                                            {this.state.hasFile ? <div>
                                            <Download class="downloadBtn" id={this.state._id}/>
                                            <Button color="danger" style={{marginRight: '10px', float: 'left'}} onClick = {(e) => {if(window.confirm("Are you sure want to delete this CV?"))this.onFileDelete(e)}}>Delete</Button> </div>:
                                            null 
                                            }
                                            {/* <Button color="danger" onClick = {(e) => {if(window.confirm("Are you sure want to delete this CV?"))this.onFileDelete(e)}}>Delete</Button> */}
                                            {/* <Button onClick={this.onFileUpload} color="primary">Upload</Button> */}
                                            
                                        </FormGroup>
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane tabId="2">
                                    <Row>
                                    <Col sm="12">
                                          <PDFComponent/> 
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