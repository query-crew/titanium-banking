import React, { useState } from 'react'
import { useSelector } from 'react-redux';
import { Route } from 'react-router-dom';
import AuthorizationService from '../services/AuthorizationService';
import Unauthorized from './Unauthorized';

function ProtectedRoute(props) {

    const [authorized, setAuthorized] = useState(false);
    AuthorizationService.authorizeUser(props.authorities, function onSuccess() {
      setAuthorized(true);
    });
    return authorized ? props.element : <Unauthorized/>;
  }
  
  export default ProtectedRoute;