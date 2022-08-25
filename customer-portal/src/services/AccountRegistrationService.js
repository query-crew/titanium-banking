import axios from "axios";

const AccountRegistrationService = {
    registerAccount: function(initialDepositAmount, accountType, onSuccess, onError) {
        axios.post(process.env.REACT_APP_ACCOUNT_API, {
            balance: initialDepositAmount,
            lastStatementDate: '2022-08-23',
            enabled: 0,
            memberId: 0,
            accountTypeId: accountType
        }).then( (response) => {
            onSuccess(response);
        }).catch( (err) => {
            onError(err);
        });
    },
    retrieveAccountTypes: function(onSuccess, onError) {
        axios.get(process.env.REACT_APP_ACCOUNTTYPES_API).then( (response) => {
            onSuccess(response);
        }).catch( (err) => {
            onError(err);
        });
    }
}
export default AccountRegistrationService;