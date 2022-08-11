import MemberRegistrationCard from '../atoms/MemberRegistrationCard';
import LandingPageNavBar from '../atoms/LandingPageNavBar';
import '../styles/RegistrationPage.css';

function RegistrationPage() {

    return (
    <div className="registration-page">
        <LandingPageNavBar signin={false} enroll home={false}/>
        <div className="registration-page-container">
            <MemberRegistrationCard/>
        </div>
    </div>
    );
  }
  
  export default RegistrationPage;