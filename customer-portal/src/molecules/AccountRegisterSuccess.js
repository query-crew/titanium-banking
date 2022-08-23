import '../styles/AccountRegister.css';
import LandingPageNavBar from "../atoms/LandingPageNavBar";
import { useNavigate } from 'react-router-dom';

const AccountRegisterSuccess = () => {

    const navigate = useNavigate();

    const returnHomeClick = () => {
        navigate(process.env.REACT_APP_HOME_ROUTE);
    }

    return (
        <div>
            <LandingPageNavBar />
            <div>
                <div className = "registerHeader">
                    Registration Successful!
                </div>
                <div className = "registerBody">
                    <button id = "returnHomeButton" className = "btn btn-primary" onClick={returnHomeClick}>Return Home</button>
                </div>
            </div>
        </div>
    );
}

export default AccountRegisterSuccess;