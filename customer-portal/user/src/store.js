import { configureStore } from "@reduxjs/toolkit";
import tokenReducer from './tokenReducer';

export const store = configureStore({reducer: 
    {token: tokenReducer}
});


// const ALL_ACCOUNTS = 'accounts/ALL_ACCOUNTS'

// const getAllAccounts = (accounts) => ({
    //     type: ALL_ACCOUNTS,
    //     accounts
    // })
    
// export const fetchAllAccounts = () => async (dispatch) => {
        //     const res = await fetch(`/accounts`)
        //     if (res.ok){
            //     const {accounts} = await res.json()
            //     dispatch(getAllAccounts(accounts))
            //     return accounts;
            //     }
            // }
const ADD_ACCOUNT = 'spots/ADD_SPOT'
const addAccount = (spot) => ({
    type: ADD_SPOT,
    spot
})

export const addNewAccount = (accountPayload) => async(dispatch) => {
    
    const res = await fetch('/accounts/add', {
        method:"POST",
        // headers: { "Content-Type" : "application/json" },
        body: accountPayload
    })
    
    const account = await res.json()
    if(res.ok){
        dispatch(addAccount(account))
    }
    console.log('()()()()()()()()()',account)
    return account
}


const initialState = {};
const accountReducer = (state = initialState, action) => {
    let newState = {...state}
    switch(action.type){
        case ALL_ACCOUNTS:
            newState = {}
            action.accounts.forEach(account => {
                newState[account.id] = account
            })
            return newState

        case ADD_ACCOUNT:
            newState[action.account.id] = action.account
            console.log(action.account)
            return newState
            default:
                return state
    }

}

export default accountReducer;


