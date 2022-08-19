import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./RegisterAccount.css"


const RegisterAccount = () => {
    const userURL = process.env.REACT_APP_USER_API;
    const accountURL = process.env.REACT_APP_ACCOUNT_API;

    // console.log(userURL);
    // console.log(accountURL);
    const navigate = useNavigate();

    const [memberId, setMemberId] = useState(1);

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
    
    const addAccount = async() => {

        const accountData = {
            "accountType": accountType,
            "accountName": accountName
        }

        const addAccount = "/accounts";
        await axios.post(accountURL + addAccount, accountData)
        .then((response) => {
            console.log(response.data);
            })
            .catch((err) => console.log(err));
    }

    const getMemberId = () => {
        const url = `/member`;
        axios.get(userURL + url, { withCredentials: true })
          .then((response) => {
            console.log(response.data)
            const { members } = response.data;
            const [ member ] = members;
            const {memberId} = member;
            setMemberId(memberId)
            console.log(memberId)
        })
        .catch((err) => console.log(err));
    }

    const getUserInfo = () => {
        const url = `/member/${memberId}`;
        axios.get(userURL + url, { withCredentials: true })
          .then((response) => {
            console.log(response.data)
            //populate form
            const { member } = response.data;
            const { memberId, firstName, lastName, phone, dateOfBirth, socialSecurityNumber, memberAddress } = member;
            // console.log(memberId, firstName, lastName, phone, dateOfBirth, socialSecurityNumber, memberAddress)
            // setMemberId(memberId)
            setFirstName(firstName)
            setLastName(lastName)
            setPhoneNumber(phone)
            setDateOfBirth(dateOfBirth)
            setAddressOne(memberAddress.addressLine1)
            setAddressTwo(memberAddress.addressLine2)
            setCity(memberAddress.city)
            setState(memberAddress.state)
            setZipcode(memberAddress.zipCode)
          })
          .catch((err) => console.log(err));
      }

    useEffect(() => {
        getMemberId();
        getUserInfo();
    })


const handleSubmit = async(e) => {
        e.preventDefault();
        const errors = [];

        if(accountType === "Account") errors.push("Please select an account type")
        // if(!firstName.trim().length) errors.push("Please enter your first name")
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
            const url = '/member/confirm'
            
            const form = {
                "firstName": firstName,
                "lastName": lastName,
                "phone": phoneNumber,
                "dateOfBirth": dateOfBirth,
                "socialSecurityNumber": ssn,
                "addressLine1": addressOne,
                "addressLine2": addressTwo,
                "city": city,
                "state": state,
                "zipcode": zipcode,
            }

                await axios.post(userURL + url, form)
                        .then((response) => {
                        addAccount();
                        console.log(response.data);
                        })
                        .catch((err) => console.log(err));
        }
}
    

    const handleCancel = e => {
        e.preventDefault();
        navigate(`/accounts`) //go back button
    } 
    
    
   return(
    <div className='container p-0 mt-5  rounded bg-light register-account-section'>
                 <span className='register-account-header mx-3 fs-1 mt-4 rounded-top'>
                     Register For Account
                 </span> 
             <div className='form-errors'>
             {validationErrors.map((error, i) => (
                    <div key={i}>{error}</div>
                    ))}
            </div>
               <div className='register-account-form bg-white' onSubmit={handleSubmit}>
                <div className="row"> 
                    <h6 className="mt-3 mx-3">Account Type</h6>
                    <select className="account-type-dropdown col-9 form-select mx-4" aria-label="Default select example" onChange={e => setAccountType(e.target.value)}>
                        <option defaultValue="Account">Account</option>
                        <option value="checking">Checking</option>
                        <option value="savings">Savings</option>
                        <option value="investing">Investing</option>
                        <option value="loan">Loan</option>
                    </select>
                    <label className="mt-4 mx-3"> Account Name </label>
                    <div className="col-9 mx-3">
                    <input className=" account-name form-control" value={accountName} onChange={e => setAccountName(e.target.value)}></input>
                    </div>
                </div>
        <div className="row">
            <div className="col ">
                     <h5 className="mt-4 mx-3"> Confirm Personal Information</h5>
                     <div className="col ">
                         <label className="mx-3 "> Name </label>
                         <div className="col-4 mx-3 w-75 input-group">
                         <input className="form-control rounded" name='firstName' placeholder='First Name'  type='text' required={true} value={firstName} onChange={ e => setFirstName(e.target.value)}/>
                         <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                         <input className="form-control rounded input-space" name='lastName' placeholder='Last Name'  type='text' required={true} value={lastName} onChange={ e => setLastName(e.target.value)}/>
                         </div>
                         <label className="mt-4 mx-3"> Phone </label>
                         <div className="col-3">
                         <input className="p-1 mx-3 form-control" name='phoneNumber' placeholder='(###)-###-####'  type='text' required={true} value={phoneNumber} onChange={ e => setPhoneNumber(e.target.value)}/>
                         </div>
                         <label className=" mt-4 mx-3"> Date of Birth</label>
                         <div className="col-3">
                         <input  className="mx-3 p-1 form-control" name='dateOfBirth' placeholder='mm-dd-yyyy'  type='text' required={true} value={dateOfBirth} onChange={ e => setDateOfBirth(e.target.value)}/>
                         </div>
                         <label className="mt-4 mx-3"> Social Security Number </label>
                         <div className="col-3">
                         <input className="mx-3 form-control" name='ssn' placeholder='###-##-####'  type='text' required={true} value={ssn} onChange={ e => setSsn(e.target.value)}/>
                         </div>
                         
                     </div>
            </div>
            <div className="col ">
      <h5 className="mx-5"> Confirm Address</h5>
                             <div className=" col mx-5">
                             <div className="col-12">
                                 <label htmlFor="inputAddress" className="form-label mt-1">Address</label>
                                 <input type="text" className="form-control" id="inputAddress" placeholder="Street Address" value={addressOne} onChange={ e => setAddressOne(e.target.value)}/>
                             </div>
                             <div className="col-12">
                                 <label htmlFor="inputAddress2" className="form-label mt-1">Address 2</label>
                                 <input type="text" className="form-control" id="inputAddress2" placeholder="Street Address 2" />
                             </div> 
                             <div className="col-md-12">
                                 <label htmlFor="inputCity" className="form-label mt-1">City</label>
                                 <input type="text" className="form-control" id="city" value={city} onChange={ e => setCity(e.target.value)} />
                             </div>
                             <div className="col-md-4">
                                 <label htmlFor="inputState" className="form-label mt-1">State</label>
                                 <select id="state" className="form-select" value={state} onChange={ e => setState(e.target.value)}>
                                 <option defaultValue="choose">Choose...</option>
                                 <option value="AL">Alabama</option>
                                 <option value="AK">Alaska</option>
                                 <option value="AZ">Arizona</option>
                                 <option value="AR">Arkansas</option>
                                 <option value="CA">California</option>
                                 <option value="CO">Colorado</option>
                                 <option value="CT">Connecticut</option>
                                 <option value="DE">Delaware</option>
                                 <option value="DC">District of Colombia</option>
                                 <option value="FL">Florida</option>
                                 <option value="GA">Georgia</option>
                                 <option value="HI">Hawaii</option>
                                 <option value="ID">Idaho</option>
                                 <option value="IL">Illinois</option>
                                 <option value="IN">Indiana</option>
                                 <option value="IA">Iowa</option>
                                 <option value="KS">Kansas</option>
                                 <option value="KY">Kentucky</option>
                                 <option value="LA">Louisiana</option>
                                 <option value="ME">Maine</option>
                                 <option value="MD">Maryland</option>
                                 <option value="MA">Massachusetts</option>
                                 <option value="MI">Michigan</option>
                                 <option value="MN">Minnesota</option>
                                 <option value="MS">Mississippi</option>
                                 <option value="MO">Missouri</option>
                                 <option value="MT">Montana</option>
                                 <option value="NE">Nebraska</option>
                                 <option value="NV">Nevada</option>
                                 <option value="NH">New Hampshire</option>
                                 <option value="NJ">New Jersey</option>
                                 <option value="NM">New Mexico</option>
                                 <option value="NY">New York</option>
                                 <option value="NC">North Carolina</option>
                                 <option value="ND">North Dakota</option>
                                 <option value="OH">Ohio</option>
                                 <option value="OK">Oklahoma</option>
                                 <option value="OR">Oregon</option>
                                 <option value="PA">Pennsylvania</option>
                                 <option value="RI">Rhode Island</option>
                                 <option value="SC">South Carolina</option>
                                 <option value="SD">South Dakota</option>
                                 <option value="TN">Tennessee</option>
                                 <option value="TX">Texas</option>
                                 <option value="UT">Utah</option>
                                 <option value="VT">Vermont</option>
                                 <option value="VA">Virginia</option>
                                 <option value="WA">Washington</option>
                                 <option value="WV">West Virginia</option>
                                 <option value="WI">Wisconsin</option>
                                 <option value="WY">Wyoming</option>     
                                 </select>
                             </div>
                             <div className="col-md-2 mb-3">
                                 <label htmlFor="inputZip" className="form-label mt-1">Zip</label>
                                 <input type="text" className="form-control" id="inputZip" value={zipcode} onChange={ e => setZipcode(e.target.value)} />
                             </div>
                         </div>
     
                 </div>
            </div>
        </div>
                
                    <div className='register-account-btns  border-top p-3 register-form-btns bg-light rounded'> 
                        <button className='go-back  text-white  btn-lg ' type='click' onClick={handleCancel}>Go Back</button>
                        <button className='register-account-submit float-end text-white  btn-lg ' type="submit" onSubmit={handleSubmit}>Register</button>
                </div>
    </div>
   
    );
    
}

export default RegisterAccount;

