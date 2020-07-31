import React, { Component } from 'react';
import { connect } from 'react-redux';
import { removeCharacterById } from '../actions';

class HeroList extends Component{
    render(){
        return(
            <div>
                <h4>Your Heroes Squad:</h4>
                <ul className="list-group">
                    {
                        this.props.heroes.map( hero => {
                            return(
                                <li key={hero.id} className="list-group-item">
                                    <div className="list-item">
                                        {hero.name}
                                    </div>
                                    <div onClick={() => this.props.removeCharacterById(hero.id)} 
                                            className="list-item right-button">
                                        x
                                    </div>
                                </li>
                            )
                        })
                    }
                </ul>
            </div>
        )
    }
}

function mapToStateProps(state){
    return {
        heroes : state.heroes
    };
}

export default connect(mapToStateProps, { removeCharacterById })(HeroList);