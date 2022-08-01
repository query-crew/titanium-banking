import { configureStore } from "@reduxjs/toolkit";
import tokenReducer from './tokenReducer';

export const store = configureStore({reducer: 
    {token: tokenReducer}
});


