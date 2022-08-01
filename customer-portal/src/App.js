import SignInCard from './atoms/SignInCard';
import MemberRegistrationCard from './atoms/MemberRegistrationCard';
import Enroll from './molecules/Enroll';
import Account from './molecules/Account';
import PasswordHelp from './molecules/PasswordHelp';
import LandingPageNavBar from './atoms/LandingPageNavBar';
import HomePageSignIn from './elements/HomePageSignIn';
import BankCard from './atoms/BankCard';
import ExpandedSignIn from './atoms/ExpandedSignIn';
import ExpandedSignInCard from './atoms/ExpandedSignInCard';
import SignInPage from './molecules/SignInPage';
import React from 'react';
import "./styles/bootstrap.css";
import "@fontsource/bungee-hairline";
import { store } from "./redux/store";
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import { Provider } from 'react-redux';
import AuthorizationService from "./services/AuthorizationService";
import ProtectedRoute from './molecules/ProtectedRoute';
import Unauthorized from './molecules/Unauthorized';

function App() {

  return (
    <Provider store={store}>
      <div className="App">
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePageSignIn/>}/>
            <Route path='/signin' element={<SignInPage/>}/>
            <Route path='/enroll' element={<MemberRegistrationCard/>}/>
            <Route path="/account" element={<ProtectedRoute element={<Account/>} authorities={["member"]}/>}/>
            <Route path="/password-help" element={<PasswordHelp/>}/>
            <Route path="/unauthorized" element={<Unauthorized/>}/>
          </Routes>
        </BrowserRouter>
      </div>
    </Provider>
  );
}

export default App;
