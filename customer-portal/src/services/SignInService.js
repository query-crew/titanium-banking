import axios from "axios";
import { setToken } from '../redux/tokenReducer';

const SignInService = {
    toggleVisibility: function(passwordVisible) {
        return !passwordVisible;
    },
    getInitialUsername: function() {
        const initUsername = localStorage.getItem("username");
        if (initUsername) {
            return initUsername;
        }
        else {
            return "";
        }
    },
    signIn: function(username, password, checked, dispatchToken, navigateAfterLogin) {
        handleRememberMe(username, checked, function onSuccess() {
            loginApiCall(
                {username: username, password: password},
                function onSuccess(token) {
                    dispatchToken(token);
                    navigateAfterLogin('/account');
                },
                function onError(err) {
                    if (typeof err == "string") {
                        alert(err);
                    }
                    else {
                        let alertMessage = "";
                        const values = Object.values(err);
                        for (const value of values) {
                            alertMessage += value + "\n";
                        }
                        alert(alertMessage);
                    }
            });
        },
        function onError(err) {
            alert(err.message);
        });
    }
}

function loginApiCall(login, onSuccess, onError) {
    axios.post("/user/login", login)
    .then(response => { 
        onSuccess(setToken(response.data));
    })
    .catch(({ response }) => onError(response.data));
}

function saveUsername(username) {
    return new Promise((resolve, reject) => {
        if(username !== "") {
            try {
                localStorage.setItem("username", username);
                localStorage.setItem("checked", "true");
                resolve();
            }
            catch (err) {
                reject(err);
            }
        }
        else {
            reject(new Error("Username is blank."));
        }
    });
}

function clearUsername(onSuccess, onError) {
    return new Promise((resolve, reject) => {
        try {
            localStorage.removeItem("username");
            localStorage.removeItem("checked");
            resolve();
        }
        catch (err) {
            reject(err);
        }
    });
}

function handleRememberMe(username, checked, onSuccess, onError) {
    if (checked) {
        try {
            saveUsername(username);
            onSuccess();
        }
        catch (err) {
            onError(err);
        }
    }
    else {
        try {
            clearUsername();
            onSuccess();
        }
        catch (err) {
            onError(err);
        }
    }
}

export default SignInService;