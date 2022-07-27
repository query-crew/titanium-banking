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

// const initialState = {};
// const accountReducer = (state = initialState, action) => {
//     let newState = {...state}
//     switch(action.type){
//         case ALL_ACCOUNTS:
//             newState = {}
//             action.accounts.forEach(account => {
//                 newState[account.id] = account
//             })
//             return newState

//             default:
//                 return state
//     }

// }

// export default accountReducer;


