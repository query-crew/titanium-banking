import React, { useState } from 'react';
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setToken } from './tokenReducer';
import Account from './components/Account';
import { BrowserRouter, Route } from 'react-router-dom'

function Login() {

  const[users, setUsers] = useState([]);
  const[usersLoading, setUsersLoading] = useState(false);
  const token = useSelector((state) => state.token.value)
  const dispatch = useDispatch();

  
  const showUsers = () => {
      axios.get("/user", )
      .then((response) => {
        setUsers(response.data);
        console.log(users);
      })
      .catch((err) => console.log(err));
  }

  
  return (
<<<<<<< HEAD:customer-portal/user/src/App.js
    <BrowserRouter>
    {/* <div className="App">
=======
    <div className="Login">
>>>>>>> QC-138:customer-portal/src/Login.js
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button onClick={loginAsAdmin}>Login as admin</button>
        <button onClick={loginAsUser}>Login as user</button>
        {usersLoading && <p>Loading...</p>}
        <button onClick={showUsers}>Show Users</button>
        {users.map((user, i) => (
          <p key={i}>{user.username}</p>
        ))}
<<<<<<< HEAD:customer-portal/user/src/App.js
      </header> */}

      <Route path='/accounts' exact={true} >
        <Account />
      </Route>
    {/* </div> */}
    </BrowserRouter>
=======
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
      </header>
    </div>
>>>>>>> QC-138:customer-portal/src/Login.js
  );
}

export default App;