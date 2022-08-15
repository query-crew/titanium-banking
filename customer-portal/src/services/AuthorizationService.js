import axios from "axios";

const AuthorizationService = {
    authorizeUser: function(authorities, onSuccess) {
        axios.get(this.authorizeApiPath, {withCredentials: true})
            .then(response => { 
                if (authorities.includes(response.data)) {
                    onSuccess();
                }
            })
            .catch(err => {
                console.log(err);
                if (err.response.status !== 401) {
                    alert(err);
                }
            });
        },
        authorizeApiPath: process.env.REACT_APP_USER_API + "/user/authorize",
}
export default AuthorizationService;