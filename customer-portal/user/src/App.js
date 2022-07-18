import logo from './logo.svg';
import React, { useState } from 'react';
import './App.css';
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setToken } from './tokenReducer';

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
      axios.get("/user", {headers: {"Authorization": `Bearer ${token}`}})
      .then((response) => {
        setUsers(response.data);
        console.log(users);
      })
      .catch((err) => console.log(err));
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button onClick={loginAsAdmin}>Login as admin</button>
        <button onClick={loginAsUser}>Login as user</button>
        {usersLoading && <p>Loading...</p>}
        <button onClick={showUsers}>Show Users</button>
        {users.map((user, i) => (
          <p key={i}>{user.username}</p>
        ))}
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
