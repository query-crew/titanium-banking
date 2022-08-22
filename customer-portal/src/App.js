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
import BranchPage from './molecules/BranchPage';
import React from 'react';
import "./styles/bootstrap.css";
import "@fontsource/bungee-hairline";
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import { Provider } from 'react-redux';
import Navbar from './Navbar';
import RegisterAccount from './RegisterAccount';
import AccountRegister from './molecules/AccountRegister';
import AuthorizationService from "./services/AuthorizationService";
import ProtectedRoute from './molecules/ProtectedRoute';
import Unauthorized from './molecules/Unauthorized';
import RegistrationPage from './molecules/RegistrationPage';
import "./App.css"
function App() {

  return (
      <div className="App">
        <Navbar />
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePageSignIn/>}/>
            <Route path='/signin' element={<SignInPage/>}/>
            <Route path='/enroll' element={<RegistrationPage/>}/>
            <Route path="/accounts" element={<ProtectedRoute element={<Account/>} authorities={["member"]}/>}/>
            <Route path="/accounts/add" element={<AccountRegister />}/>
            <Route path="/password-help" element={<PasswordHelp/>}/>
            <Route path="/branch" element={<BranchPage/>}/>
            <Route path="/unauthorized" element={<Unauthorized/>}/>
          </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;
