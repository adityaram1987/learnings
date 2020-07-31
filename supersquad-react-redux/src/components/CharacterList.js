import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { addCharacterById } from '../actions';

class CharacterList extends Component{
    render(){
        console.log('this.props', this.props);
        return(
            <div>
                <h4>Characters</h4>
                <ul className="list-group">
                    {
                        this.props.characters.map(character =>{
                            return (
                                <li key={character.id} className="list-group-item">
                                    <div className="list-item">
                                        {character.name}
                                    </div>
                                    <div onClick={() => this.props.addCharacterById(character.id)} 
                                            className="list-item right-button">
                                        +
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
        characters : state.characters
    };
}

// function mapDispatchToProps(dispatch){
//     return bindActionCreators({ addCharacterById }, dispatch)
// }

// export default connect(mapToStateProps, mapDispatchToProps)(CharacterList);
export default connect(mapToStateProps, { addCharacterById })(CharacterList);