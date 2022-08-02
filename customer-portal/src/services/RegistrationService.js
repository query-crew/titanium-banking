import axios from "axios";
import { ToastContainer, toast } from 'react-toastify';
import validator from 'validator';

const RegistrationService = {
    listOfStates: [{"Alabama": "AL"}, {"Alaska": "AK"}, {"Arizona": "AZ"}, {"Arkansas": "AR"}, {"California": "CA"},
    {"Colorado": "CO"}, {"Connecticut": "CT"}, {"Delaware": "DE"}, {"Florida": "FL"}, {"Georgia": "GA"}, {"Hawaii": "HI"},
    {"Idaho": "ID"}, {"Illinois": "IL"}, {"Indiana": "IN"}, {"Iowa": "IA"}, {"Kansas": "KS"}, {"Kentucky": "KY"},
    {"Louisiana": "LA"}, {"Maine": "ME"}, {"Maryland": "MD"}, {"Massachusetts": "MA"}, {"Michigan": "MI"},
    {"Minnesota": "MN"}, {"Mississippi": "MS"}, {"Missouri": "MO"}, {"Montana": "MT"}, {"Nebraska": "NE"},
    {"Nevada": "NV"}, {"New Hampshire": "NH"}, {"New Jersey": "NJ"}, {"New Mexico": "NM"}, {"New York": "NY"},
    {"North Carolina": "NC"}, {"North Dakota": "ND"}, {"Ohio": "OH"}, {"Oklahoma": "OK"}, {"Oregon": "OR"},
    {"Pennsylvania": "PA"}, {"Rhode Island": "RI"}, {"South Carolina": "SC"}, {"South Dakota": "SD"}, 
    {"Tennessee": "TN"}, {"Texas": "TX"}, {"Utah": "UT"}, {"Vermont": "VT"}, {"Virginia": "VA"}, {"Washington": "WA"},
    {"West Virginia": "WV"}, {"Wisconsin": "WI"}, {"Wyoming": "WY"}, {"District of Columbia": "DC"}, {"American Samoa": "AS"},
    {"Guam": "GU"}, {"Northern Mariana Islands": "MP"}, {"Puerto Rico": "PR"}, {"U.S. Virgin Islands": "VI"}],
    formValid: function(email, username, password, firstName, lastName, phone, 
        dob, social, addressLine1, addressLine2, state, city, zipcode) {
        if (validator.isEmail(email) && !validator.isEmpty(username) && this.isUsername(username) &&
        validator.isStrongPassword(password, {minLength: 8, minLowercase: 1, minUppercase: 1, minNumbers: 1, minSymbols: 0}) &&
        this.isName(firstName) && this.isName(lastName) && validator.isMobilePhone(phone, 'en-US') && validator.isDate(dob) && 
        this.isSocial(social) && this.isAddressOne(addressLine1) && this.isAddressTwo(addressLine2) && this.isState(state) && this.isCity(city) &&
        validator.isPostalCode(zipcode, 'US')) {
            return true;
        }
        return false;
    },
    isUsername: function(username) {
        if (/^[a-zA-Z\d_.-]+$/.test(username)) {
            return true;
        }
        return false;
    },
    isName: function(name) {
        if (/^[^!@#$%^&*(),.?":{}|<>\d]+$/.test(name)) {
            return true;
        }
        return false;
    },
    isSocial: function(social) {
        if (/^[0-9]{3}-[0-9]{2}-[0-9]{4}$/.test(social)) {
            return true;
        }
        return false;
    },
    isAddressOne: function(addressLine1) {
        if (/^[\da-z-A-Z.\s-]+$/.test(addressLine1)) {
            return true;
        }
        return false;
    },
    isAddressTwo: function(addressLine2) {
        if (/^(?![\s\S])|[\da-z-A-Z.-]+$/.test(addressLine2)) {
            return true;
        }
        return false;
    },
    isCity: function(city) {
        if(/^[a-zA-Z]+$/.test(city)) {
            return true;
        }
        return false;
    },
    isState: function(state) {
        return state !== "State";
    },
    registrationErrorToast: function(message) {
        toast.error(message, {
            className: 'black-background',
            position: "top-right",
            autoClose: true,
            hideProgressBar: true,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            });
    },
    checkRegistration: function(email, username, password, firstName, lastName, phone, dob, social,
        addressLine1, addressLine2, city, state, zipcode, disabled) {
        if (disabled) {
            if (validator.isEmpty(email)) {
                this.registrationErrorToast("Email is blank");
            }
            else if (!validator.isEmail(email)) {
                this.registrationErrorToast("Email is invalid");
            }
            else if (validator.isEmpty(username)) {
                this.registrationErrorToast("Username is blank");
            }
            else if (!this.isUsername(username)) {
                this.registrationErrorToast("Username can only contain alphanumeric characters, underscores, dashes, and dots.");
            }
            else if (validator.isEmpty(password)) {
                this.registrationErrorToast("Password is blank");
            }
            else if (!validator.isStrongPassword(password, {minLength: 8, minLowercase: 1, minUppercase: 1, minNumbers: 1, minSymbols: 0})) {
                this.registrationErrorToast("Password must contain 8 characters, one digit, one uppercase letter, and one lowercase letter. Special characters are not allowed.");
            }
            else if (validator.isEmpty(firstName)) {
                this.registrationErrorToast("First name is blank");
            }
            else if (validator.isEmpty(lastName)) {
                this.registrationErrorToast("Last name is blank");
            }
            else if (!this.isName(firstName) && !this.isName(lastName)) {
                this.registrationErrorToast("Names cannot contain special characters or numbers.");
            }
            else if (validator.isEmpty(phone)) {
                this.registrationErrorToast("Phone is blank");
            }
            else if (!validator.isMobilePhone(phone, 'en-US')) {
                this.registrationErrorToast("Phone must be formatted: (###)###-####");
            }
            else if (validator.isEmpty(dob)) {
                this.registrationErrorToast("Date of birth is blank");
            }
            else if (!validator.isDate(dob)) {
                this.registrationErrorToast("Date of birth must be formatted: (YYYY)-(MM)-(DD)");
            }
            else if (validator.isEmpty(social)) {
                this.registrationErrorToast("Social security number is blank");
            }
            else if (!this.isSocial(social)) {
                this.registrationErrorToast("Social security number must be formatted: ###-##-####");
            }
            else if (validator.isEmpty(addressLine1)) {
                this.registrationErrorToast("Address line 1 is blank");
            }
            else if (!this.isAddressOne(addressLine1)) {
                this.registrationErrorToast("Address must be alphanumeric and can only contain dots and dashes.");
            }
            else if (!this.isAddressTwo(addressLine2)) {
                this.registrationErrorToast("Address must be alphanumeric and can only contain dots and dashes.");
            }
            else if (validator.isEmpty(city)) {
                this.registrationErrorToast("City is blank");
            }
            else if (!this.isCity(city)) {
                this.registrationErrorToast("City must be alphanumeric.");
            }
            else if (validator.isEmpty(state)) {
                this.registrationErrorToast("State is blank");
            }
            else if (!this.isState(state)) {
                this.registrationErrorToast("State must be alphanumeric.");
            }
            else if (validator.isEmpty(zipcode)) {
                this.registrationErrorToast("Zipcode is blank");
            }
            else if (!validator.isPostalCode(zipcode, 'US')) {
                this.registrationErrorToast("Zipcode must be formatted: #####-####");
            }
        }
    },
    formatPhone: function(phone) {
        let phoneVal = phone;
        if (phoneVal.length > 3 && phoneVal.length < 7 && /^[^\(\)-]+$/.test(phoneVal)) {
            phoneVal = "(" + phoneVal.slice(0, 3) + ")" + phoneVal.slice(3);
            return phoneVal;
        }
        else if (phoneVal.length > 4 && phoneVal.length < 8 && /^[^\)-]+$/.test(phoneVal)) {
            phoneVal = phoneVal.slice(0, 4) + ")" + phoneVal.slice(4);
            return phoneVal;
        }
        else if (phoneVal.length > 8 && phoneVal.length < 13 && /^[^-]+$/.test(phoneVal)) {
            phoneVal = phoneVal.slice(0, 8) + "-" + phoneVal.slice(8);
            return phoneVal;
        }
        return phoneVal;
    },
    formatSocial: function(social) {
        let socialValue = social;
        if (socialValue.length > 3 && socialValue.length < 6 && /^[^-]+$/.test(socialValue)) {
            socialValue = socialValue.slice(0,3) + "-" + socialValue.slice(3);
            return socialValue;
        }
        else if (socialValue.length > 6 && socialValue.length < 11 && /^[^-]+-[^-]+$/.test(socialValue)) {
            socialValue = socialValue.slice(0, 6) + "-" + socialValue.slice(6);
            return socialValue;
        }
        return socialValue;
    },
    formatZipcode: function(zipcode) {
        let zipcodeValue = zipcode;
        if (zipcodeValue.length > 5 && zipcodeValue.length < 10 && /^[^-]+$/.test(zipcodeValue)) {
            zipcodeValue = zipcodeValue.slice(0,5) + "-" + zipcodeValue.slice(5);
            return zipcodeValue;
        }
        else {
            return zipcodeValue;
        }
        return zipcodeValue;
    },
    addBankMember: function(email, username, password, 
        firstName, lastName, phone, dateOfBirth, 
        socialSecurityNumber, addressLine1, addressLine2, 
        city, state, zipcode, onSuccess, onError) {
            axios.post("/member", {email: email, username: username, 
                password: password, firstName: firstName, lastName: lastName,
            phone: phone, dateOfBirth: dateOfBirth, socialSecurityNumber: socialSecurityNumber, 
            addressLine1: addressLine1, addressLine2: addressLine2, city: city,
            state: state, zipcode: zipcode})
            .then(response => { 
                onSuccess();
            })
            .catch(({ response }) => {
                let alertMessage = "";
                if (typeof response.data == "string") {
                    alertMessage = response.data;
                }
                else {
                    const values = Object.values(response.data);
                    for (const value of values) {
                        alertMessage += value + "\n";
                    }
                }
                RegistrationService.registrationErrorToast(alertMessage);
            });
        }
}

export default RegistrationService;
