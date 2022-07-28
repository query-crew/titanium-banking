import SignInCard from './atoms/SignInCard';
import Enroll from './Enroll';
import Account from './Account';
import PasswordHelp from './PasswordHelp';
import BankNavBar from './atoms/BankNavBar';
import HomePageSignIn from './elements/HomePageSignIn';
import BankCard from './atoms/BankCard';
import ExpandedSignIn from './atoms/ExpandedSignIn';
import ExpandedSignInCard from './atoms/ExpandedSignInCard';
import SignInPage from './molecules/SignInPage';
import React from 'react';
import "./bootstrap.css";
import "@fontsource/bungee-hairline";
import { store } from "./store";
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import { Provider } from 'react-redux';
import Navbar from './Navbar';
import RegisterAccount from './RegisterAccount';

function App() {

  return (
    <Provider store={store}>
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePageSignIn/>}/>
            <Route path='/signin' element={<SignInPage/>}/>
            <Route path='/enroll' element={<Enroll/>}/>
            <Route path="/accounts" element={<Account />}/>
            <Route path="/accounts/add" element={<RegisterAccount />}/>
            <Route path="/password-help" element={<PasswordHelp/>}/>
          </Routes>
        </BrowserRouter>
      </div>
    </Provider>
  );
}

export default App;
