import React, { useState, useEffect } from 'react'
import { useSelector } from 'react-redux';
import { Route } from 'react-router-dom';
import AuthorizationService from '../services/AuthorizationService';
import Unauthorized from './Unauthorized';
import Spinner from 'react-bootstrap/Spinner';

function ProtectedRoute(props) {

    const [authorized, setAuthorized] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      AuthorizationService.authorizeUser(props.authorities, function onSuccess() {
        setAuthorized(true);
        setLoading(false);
      });
    }, [])

    return (
      <div>
        {loading ?
        <div className="d-flex vh-100 align-items-center justify-content-center">
          <Spinner animation="border" role="status" style={{ height: "100px", width: "100px" }}>
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </div>
          :
          <div>
            {authorized ? props.element : <Unauthorized/>}
          </div>
        }
      </div>
    )
  }
  
  export default ProtectedRoute;