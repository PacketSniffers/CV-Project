import React from 'react';
import PDF from 'react-pdf-js';
import {Input,ButtonGroup,Button,FormGroup} from 'reactstrap';
 
class PDFComponent extends React.Component {

    constructor(props) {
      super(props);
      this.state = {
        filename: this.props.filename 
      };
    };

  static defaultProps = {filename:"Bb.pdf"}
  
  onDocumentComplete = (pages) => {
    this.setState({ page: 1, pages });
  }
  handlePrevious = () => {
    this.setState({ page: this.state.page - 1 });
  }
 
  handleNext = () => {
    this.setState({ page: this.state.page + 1 });
  }

  renderPagination = (page, pages) => {
    let previousButton = <Button color="primary" style={{listStyleType: 'none'}} className="previous" onClick={this.handlePrevious}><a style={{color: 'white'}} href="#"><i className="fa fa-arrow-left"></i> Prev</a></Button>;
    if (page === 1) {
      previousButton = <Button color="primary" style={{listStyleType: 'none'}} className="previous disabled"><a style={{color: 'white'}} href="#"><i className="fa fa-arrow-left"></i> Prev</a></Button>;
    }
    let nextButton = <Button color="primary" style={{listStyleType: 'none'}} className="next" onClick={this.handleNext}><a style={{color: 'white'}} href="#">Next <i className="fa fa-arrow-right"></i></a></Button>;
    if (page === pages) {
      nextButton = <Button color="primary" style={{listStyleType: 'none'}} className="next disabled"><a style={{color: 'white'}} href="#">Next <i className="fa fa-arrow-right"></i></a></Button>;
    }
    return (
            <ButtonGroup style={{float: 'right'}} size="sm" className="pager">
                {previousButton}
                {nextButton}
            </ButtonGroup>
      );
  }

  
  
  render() {
    let pagination = null;
    if (this.state.pages) {
      pagination = this.renderPagination(this.state.page, this.state.pages);
    }
    return (
      <div>
        {pagination}
        {/* {this.state.filename}
        {(this.state.filename) ? */}
        <PDF
            file="DanHiggins.pdf" 
            onDocumentComplete={this.onDocumentComplete}
            page={this.state.page}
            />
            {/* : <FormGroup>
        <br/>
        <h6 for="exampleFile">Upload CV</h6>
        <Input type="file" name="file" id="exampleFile" />
        <br/>
        <Button color="primary">Upload</Button>
      </FormGroup> */}
      </div>
    )
  }
}
 
export default PDFComponent;