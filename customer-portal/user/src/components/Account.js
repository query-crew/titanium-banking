import { useEffect, useState } from "react";
import axios from "axios";

const Account = () => {
    const [accounts, getAccounts] = useState('')
    const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD', 
    });
    const url = 'http://localhost:8080/accounts'
    
    useEffect(() => {
      getAllAccounts()
    }, []);

    const getAllAccounts = () => {
        axios.get(`${url}`)
          .then((response) => {
            const allAccounts = (response.data);
            console.log(allAccounts);
            getAccounts(allAccounts)
          })
          .catch((err) => console.log(err));
      }


    return(
        <div className="container mt">
            <h6 className="text-black-50">All Accounts</h6>
            <div className="row">
                <div className="col border-end border-info align-self-start px-0">
                    {accounts && accounts.map((account, i) => 
                        <div className="card-body" key={i}>
                            <div className="card  rounded-0" >
                                <span className="card-title d-flex justify-content-between">{account.accountName} <span className="pe-1">{formatter.format(account.balance)}</span></span>
                                <span className="card-subtitle mb-2 text-muted">{account.accountNumber}</span>
                            </div>
                         </div>
                    )}
            </div>
                <div className="col-6 border border-primary align-self-center">
                <h5>Free Checking</h5>
                    <ul className="nav justify-content-center border-bottom">
                        <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="#">Transactions</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Cards</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Invest</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Account Details</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Analytics</a>
                        </li>
                    </ul>
                </div>
                <div className="col border border-warning align-self-end">
                    <div className="card-body">
                            <div className="card" > 
                                <h6 className="card-title border-bottom mb-4 bg-light text-center p-2" >Actions</h6>
                               
                                    <button className="dashboard-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Dashboard</button>
                                    <button className="accounts-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Accounts</button>
                                    <button className="transfers-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Transfers</button>
                                    <button className="loans-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Loans</button>
                                    <button className="new-account-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Open A New Account</button>
                               
                            </div>
            
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Account;