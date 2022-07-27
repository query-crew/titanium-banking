import React, { useState } from 'react';
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setToken } from './tokenReducer';

function Login() {

  const[users, setUsers] = useState([]);
  const[usersLoading, setUsersLoading] = useState(false);
  const token = useSelector((state) => state.token.value)
  const dispatch = useDispatch();

  
  const showUsers = () => {
      axios.get("/user", {headers: {"Authorization": `Bearer ${token}`}})
      .then((response) => {
        setUsers(response.data);
        console.log(users);
      })
      .catch((err) => console.log(err));
  }

  return (
    <div className="Login">
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
      </header>
    </div>
  );
}

export default App;