import { ADD_CHARACTER, REMOVE_CHARACTER } from '../actions';
import { createCharacter } from './helper';

function heroes(state = [], action){
    switch(action.type){
        case ADD_CHARACTER:
            let heroes = [...state, createCharacter(action.id)]
            return heroes;
        case REMOVE_CHARACTER:
            let currentHeroes = state.filter(item => item.id !==action.id)
            return currentHeroes;
        default:
            return state;
    }
}

export default heroes;