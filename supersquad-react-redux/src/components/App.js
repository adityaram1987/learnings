import React, { Component } from 'react';
import CharacterList from './CharacterList';
import '../styles/index.css';
import HeroList from './HeroList';
import SquadStats from './SquadStats';

class App extends Component{
    render(){
        console.log('In App main class')
        return(
            <div className="App">
                <h2>Super Squad</h2>
                <div className="row">
                    <div className="col-4">
                        <CharacterList/>
                    </div>
                    <div className="col-4">
                        <HeroList/>
                    </div>
                    <div className="col-4">
                        <SquadStats/>
                    </div>
                </div>
            </div>
        )
    }
}

export default App;