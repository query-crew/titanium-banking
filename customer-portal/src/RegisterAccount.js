import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./RegisterAccount.css"


const RegisterAccount = () => {

    const navigate = useNavigate();
    // const sessionUser = useSelector(state => state.session.user);
    
    const [accountType, setAccountType] = useState('Account');
    const [accountName,  setAccountName] = useState('');
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
    
    const [validationErrors, setValidationErrors] = useState([])

    console.log("&*&(*&(&(*&*(&(*&(*", accountType);

const handleSubmit = async(e) => {
        e.preventDefault();
        // console.log(e.target)
        let formData = new FormData(e.target)
        const errors = [];

        if(accountType === "Account") errors.push("Please select an account type")
        if(!firstName.trim().length) errors.push("Please enter your first name")
        if(!lastName.trim().length) errors.push("Please enter your last name")
        if(!phoneNumber.trim().length) errors.push("Please enter your phone number")
        if(!dateOfBirth.trim().length) errors.push("Please enter your date of birth")
        if(!ssn.trim().length) errors.push("Please enter your social security number")
        if(!addressOne.trim().length) errors.push("Please enter your address")
        if(!city.trim().length) errors.push("Please enter a city")
        if(!state.trim().length) errors.push("Please enter a state")
        if(!zipcode.trim().length) errors.push("Please enter your zipcode")
        
        

        if (errors.length){
            setValidationErrors(errors)
        } else {
            const url = 'http://localhost:8080/accounts'
            // const config = { headers: {
            //     'Content-Type': 'multipart/form-data'
            // }}

            
            const form = {
                "accountType": accountType,
                "accountName": accountName,
                // "firstName": firstName,
                // "lastName": lastName,
                // "phoneNumber": phoneNumber,
                // "dateOfBirth": dateOfBirth,
                // "ssn": ssn,
                // "addressOne": addressOne,
                // "addressTwo": addressTwo,
                // "city": city,
                // "state": state,
                // "zipcode": zipcode,
            }

           
                await axios.post(`${url}`, form)
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
                <span className='register-account-header fs-1 mt-4 bg-light rounded-top'>
                    Register for Account
                </span>  
            <div className='form-errors'>
            {validationErrors.map((error, i) => (
                <div key={i}>{error}</div>
                ))}
            </div>
           <form className='register-account-form bg-white'
                 onSubmit={handleSubmit}>
                <h6 className="mt-3 mx-3">Account Type</h6>
                <select className="account-type-dropdown col-9 form-select mx-3" aria-label="Default select example" onChange={e => setAccountType(e.target.value)}>
                    <option defaultValue="Account">Account</option>
                    <option value="checking">Checking</option>
                    <option value="savings">Savings</option>
                    <option value="investing">Investing</option>
                    <option value="loan">Loan</option>
                </select>
                <label> Account Name </label>
                <input className="account-name col-4 mt-3" value={accountName} onChange={e => setAccountName(e.target.value)}></input>
                <h5 className="mt-5"> Confirm Personal Information</h5>
                    <label className="mx-3"> Name </label>
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
                <h5> Confirm Address</h5>
                <div class="col-12">
                    <label for="inputAddress" class="form-label">Address</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Street Address" />
                </div>
                <div class="col-12">
                    <label for="inputAddress2" class="form-label">Address 2</label>
                    <input type="text" class="form-control" id="inputAddress2" placeholder="Street Address 2" />
                </div>
                <div class="col-md-6">
                    <label for="inputCity" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" />
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">State</label>
                    <select id="state" class="form-select">
                    <option selected>Choose...</option>
                    <option value="AL">...</option>
                    <option value="AK">...</option>
                    <option value="AZ">...</option>
                    <option value="AR">...</option>
                    <option value="AS">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                    <option value="">...</option>
                     
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Zip</label>
                    <input type="text" class="form-control" id="inputZip" />
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

