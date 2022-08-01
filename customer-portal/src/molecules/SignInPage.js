import ExpandedSignInCard from '../atoms/ExpandedSignInCard';
import ExpandedSignIn from '../atoms/ExpandedSignIn';
import LandingPageNavBar from '../atoms/LandingPageNavBar';
import '../styles/SignInPage.css';
function SignInPage() {

    return (
    <div>
        <LandingPageNavBar signin home={false}/>
        <div className="sign-in-page-container d-flex justify-content-center align-items-center vh-100">
            <ExpandedSignInCard/>
            <ExpandedSignIn/>
        </div>
    </div>
    );
  }
  
  export default SignInPage;