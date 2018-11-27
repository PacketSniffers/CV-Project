import React, {Component} from 'react';


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