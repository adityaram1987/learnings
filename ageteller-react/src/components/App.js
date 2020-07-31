import React, { Component } from 'react';
import { Form, FormControl, Button } from 'react-bootstrap';
import './App.css';
import AgeStats from './AgeStats.js';

class App extends Component{
    constructor(){
        super();
        this.state = {
            newDate: '',
            birthDay: '1997-04-10',
            showStatus: false
        }
    }

    changeBirthday(){
        console.log(this.state);
        this.setState({birthDay : this.state.newDate});
        this.setState({showStatus : true});
    }

    render(){
        return(
            <div className='App'>
                <Form>
                    <h2>Input your birthday!</h2>
                    <FormControl type="date" onChange= {event => this.setState({newDate: event.target.value})}>
                    </FormControl>
                    {' '}
                    <Button onClick = {() => this.changeBirthday()}>
                        Submit
                    </Button>
                    {
                        this.state.showStatus 
                            ? <div className="fade age-stats"><AgeStats birthday={this.state.birthDay}/></div> 
                            : <div></div>
                    }
                </Form>
            </div>
        )
    }
}

export default App;