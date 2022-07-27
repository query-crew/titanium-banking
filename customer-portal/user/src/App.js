import logo from './logo.svg';
import React, { useState } from 'react';
import './App.css';
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setToken } from './tokenReducer';
import Account from './components/Account';
import { BrowserRouter, Route } from 'react-router-dom'
import Navbar from './components/Navbar';
import RegisterAccount from './components/RegisterAccount';

function App() {

  const[users, setUsers] = useState([]);
  const[usersLoading, setUsersLoading] = useState(false);
  const token = useSelector((state) => state.token.value)
  const dispatch = useDispatch();

  const loginAsAdmin = () => {
    const login = { username: 'chloeadmin', password: 'mypasswrd'};
    setUsersLoading(true);
    axios.post("/user/login", login)
    .then(response => dispatchToken(response.data))
    .catch((err) => console.log(err));
  }

  const loginAsUser = () => {
    const login = { username: 'test10', password: 'testpassssss'};
    setUsersLoading(true);
    axios.post("/user/login", login)
    .then(response => dispatchToken(response.data))
    .catch((err) => alert(err));
  }

  async function dispatchToken(token) {
    await dispatch(setToken(token));
    setUsersLoading(false);
  }
  
  const showUsers = () => {
      axios.get("/user", )
      .then((response) => {
        setUsers(response.data);
        console.log(users);
      })
      .catch((err) => console.log(err));
  }

  
  return (
    <BrowserRouter>
    <div className='App'>
      <Navbar />
      
    {/* <div className="App">
    <Navbar />
    <header className="App-header">
    <img src={logo} className="App-logo" alt="logo" />
    <button onClick={loginAsAdmin}>Login as admin</button>
    <button onClick={loginAsUser}>Login as user</button>
    {usersLoading && <p>Loading...</p>}
    <button onClick={showUsers}>Show Users</button>
    {users.map((user, i) => (
      <p key={i}>{user.username}</p>
      ))}
    </header> */}

      <Route path='/accounts' exact={true} >
        <Account />
      </Route>

      <Route path='/account/add' exact={true}>
        <RegisterAccount />
      </Route>
    </div>
    {/* </div> */}
    </BrowserRouter>
  );
}

export default App;
