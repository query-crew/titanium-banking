import React from "react";
import Spinner from "react-bootstrap/Spinner";

const LoadingSpinner = () => {
  return (
    <div className="d-flex justify-content-center">
      <Spinner animation="border"></Spinner>
    </div>
  );
};

export default LoadingSpinner;
