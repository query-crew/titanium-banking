import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import "./RegisterAccount.css"


const RegisterAccount = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    // const sessionUser = useSelector(state => state.session.user);
    
    // const [accountType, setAccountType] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [dateOfBirth, setDateOfBirth] = useState('')
    const [ssn, setSsn] = useState('')
    const [addressOne, setAddressOne] = useState('')
    const [addressTwo, setAddressTwo] = useState('')
    const [city, setCity] = useState('')
    const [state, setState] = useState('')
    const [zipcode, setZipcode] = useState('')
    const [country, setCountry] = useState('')
    const [validationErrors, setValidationErrors] = useState([])

const handleSubmit = async(e) => {
        e.preventDefault();
        let formData = new FormData(e.target)
        // formData.set('userId', sessionUser.id)
        console.log(e.target)
        const errors = [];
      
        if(!firstName.trim().length) errors.push("Please enter your first name")
        if(!lastName.trim().length) errors.push("Please enter your last name")
        if(!phoneNumber.trim().length) errors.push("Please enter your phone number")
        if(!dateOfBirth.trim().length) errors.push("Please enter your date of birth")
        if(!ssn.trim().length) errors.push("Please enter your social security number")
        if(!addressOne.trim().length) errors.push("Please enter your address")
        if(!city.trim().length) errors.push("Please enter a city")
        if(!state.trim().length) errors.push("Please enter a state")
        if(!zipcode.trim().length) errors.push("Please enter your zipcode")
        if(!country.trim().length) errors.push("Please enter a country")
        

        if (errors.length){
            setValidationErrors(errors)
        } else {
                // let createdAccount = await dispatch(addNewAccount(formData))
                // console.log('***************',createdAccount)
                // if(createdAccount) {
                //     navigate('/accounts')
                // }
            }
        }
    const handleCancel = async(e) => {
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
                <select className="col-9 form-select w-25" aria-label="Default select example">
                    <option defaultValue="Account">Account</option>
                    <option value="1">Savings</option>
                    <option value="2">Investing</option>
                    <option value="3">placeholder</option>
                </select>
                <h3> Confirm Personal Information</h3>
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
                </div>
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