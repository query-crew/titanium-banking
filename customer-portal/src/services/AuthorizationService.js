import axios from "axios";

const AuthorizationService = {
    authorizeUser: function(authorities, onSuccess) {
        axios.get("/user/authorize/")
            .then(response => { 
                if (authorities.includes(response.data)) {
                    onSuccess();
                }
            })
            .catch(err => {
                if (err.response.status !== 401) {
                    alert(err);
                }
            });
        }
}

export default AuthorizationService;