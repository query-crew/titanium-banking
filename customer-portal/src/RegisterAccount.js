import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./RegisterAccount.css"


const RegisterAccount = () => {

    const navigate = useNavigate();
    // const sessionUser = useSelector(state => state.session.user);
    
    const [accountType, setAccountType] = useState('Account');
    const [accountName,  setAccountName] = useState('');
    // const [firstName, setFirstName] = useState('');
    // const [lastName, setLastName] = useState('')
    // const [phoneNumber, setPhoneNumber] = useState('')
    // const [dateOfBirth, setDateOfBirth] = useState('')
    // const [ssn, setSsn] = useState('')
    // const [addressOne, setAddressOne] = useState('')
    // const [addressTwo, setAddressTwo] = useState('')
    // const [city, setCity] = useState('')
    // const [state, setState] = useState('')
    // const [zipcode, setZipcode] = useState('')
    // const [country, setCountry] = useState('')
    const [validationErrors, setValidationErrors] = useState([])

    console.log("&*&(*&(&(*&*(&(*&(*", accountType);

const handleSubmit = async(e) => {
        e.preventDefault();
        // console.log(e.target)
        let formData = new FormData(e.target)
        const errors = [];

        if(accountType === "Account") errors.push("Please select an account type")
        // if(!firstName.trim().length) errors.push("Please enter your first name")
        // if(!lastName.trim().length) errors.push("Please enter your last name")
        // if(!phoneNumber.trim().length) errors.push("Please enter your phone number")
        // if(!dateOfBirth.trim().length) errors.push("Please enter your date of birth")
        // if(!ssn.trim().length) errors.push("Please enter your social security number")
        // if(!addressOne.trim().length) errors.push("Please enter your address")
        // if(!city.trim().length) errors.push("Please enter a city")
        // if(!state.trim().length) errors.push("Please enter a state")
        // if(!zipcode.trim().length) errors.push("Please enter your zipcode")
        // if(!country.trim().length) errors.push("Please enter a country")
        

        if (errors.length){
            setValidationErrors(errors)
        } else {
            const url = 'http://localhost:8080/accounts'
            // const config = { headers: {
            //     'Content-Type': 'multipart/form-data'
            // }}
            // formData.append("accountType", accountType);
            // formData.append("accountName", accountName);
            // formData.append("firstName", firstName);
            // formData.append("lastName", lastName);
            // formData.append("phoneNumber", phoneNumber);
            // formData.append("dateOfBirth", dateOfBirth);
            // formData.append("ssn", ssn);
            // formData.append("addressOne", addressOne);
            // formData.append("addressTwo", addressTwo);
            // formData.append("city", city);
            // formData.append("state", state);
            // formData.append("zipcode", zipcode);
            // formData.append("country", country);

           
                await axios.post(`${url}`, {"accountType": accountType, "accountName": accountName})
                        .then((response) => {
                        console.log(response.data);
                        })
                        .catch((err) => console.log(err));
            
        }
}

// useEffect(() => {
//     addNewAccount();
// }, []);
    

    const handleCancel = e => {
        e.preventDefault();
        navigate(`/accounts`) //go back button
    } 
    
    
   return(
       <section className='container border border-danger register-account-section'>
            <div className='row'>
            
                <h1 className='register-account-header bg-light rounded-top'>
                    Register for Account
                </h1> 
                
            <div className='form-errors'>
            {validationErrors.map((error, i) => (
                <div key={i}>{error}</div>
                ))}
            </div>
           <form className='register-account-form bg-white'
                 onSubmit={handleSubmit}>
                <h4>Account Type</h4>
                <select className="col-9 form-select w-25" aria-label="Default select example" onChange={e => setAccountType(e.target.value)}>
                    <option defaultValue="Account">Account</option>
                    <option value="checking">Checking</option>
                    <option value="savings">Savings</option>
                    <option value="investing">Investing</option>
                    <option value="loan">Loan</option>
                </select>
                <label> Account Name</label>
                <input className="col-4" value={accountName} onChange={e => setAccountName(e.target.value)}></input>
                {/* <h3> Confirm Personal Information</h3>
                    <label> Name </label>
                <div className="col-4">
                    <input className="p-1 " name='firstName' placeholder='First Name'  type='text' required={true} value={firstName} onChange={ e => setFirstName(e.target.value)}/>
                    <input className="p-1" name='lastName' placeholder='Last Name'  type='text' required={true} value={lastName} onChange={ e => setLastName(e.target.value)}/>
                    <label> Phone 
                    <input className="p-1" name='phoneNumber' placeholder='(###)-###-####'  type='text' required={true} value={phoneNumber} onChange={ e => setPhoneNumber(e.target.value)}/>
                    </label>
                    <label> Date of Birth
                    <input  className="p-1" name='dateOfBirth' placeholder='mm-dd-yyyy'  type='text' required={true} value={dateOfBirth} onChange={ e => setDateOfBirth(e.target.value)}/>
                    </label>
                    <label> Social Security Number
                    <input className="p-1" name='ssn' placeholder='###-##-####'  type='text' required={true} value={ssn} onChange={ e => setSsn(e.target.value)}/>
                    </label>
                </div>
                <h3> Confirm Address</h3>
                <div className="col-6">
                    <input className="p-1" name='addressOne' placeholder='Street Address'  type='text' required={true} value={addressOne} onChange={ e => setAddressOne(e.target.value)}/>
                    <input className="p-1" name='addressTwo' placeholder='Street Address 2'  type='text' required={true} value={addressTwo} onChange={ e => setAddressTwo(e.target.value)}/>
                    <input className="p-1" name='city' placeholder='City'  type='text' required value={city} onChange={ e => setCity(e.target.value)}/>
                    <input className="p-1" name='state' placeholder='State'  type='text' required value={state} onChange={ e => setState(e.target.value)}/>
                    <input className="p-1" name='zipcode' placeholder='Zipcode'  type='text' required value={zipcode} onChange={ e => setZipcode(e.target.value)}/>
                    <input className="p-1" name='country' placeholder='Country'  type='text' required value={country} onChange={ e => setCountry(e.target.value)}/>
                </div> */}
                <div className='register-account-btns mt-3 border-top register-form-btns bg-light rounded'> 
                    <button className='go-back  text-white btn btn-primary btn-lg ' type='click' onClick={handleCancel}>Go Back</button>
                    <button className='register-account-submit float-end text-white btn btn-primary btn-lg ' type="submit">Register</button>
                </div>
           </form>

            </div>
       </section>
    );
    
}

export default RegisterAccount;