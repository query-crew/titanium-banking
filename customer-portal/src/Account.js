import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'; 


const Account = () => {
    const [accounts, getAccounts] = useState('')
    const navigate = useNavigate();
    
    const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD', 
    });

    const url = 'http://localhost:8080/accounts'
    
    const getAllAccounts = () => {
        axios.get(`${url}`)
          .then((response) => {
            const allAccounts = (response.data);
            // console.log(allAccounts);
            getAccounts(allAccounts)
          })
          .catch((err) => console.log(err));
      }

    useEffect(() => {
      getAllAccounts()
    //   console.log('i fire once');
    }, []);

    
    const toRegisterAccountPage = () => {
        let path = '/accounts/add';
        navigate(path)
    }

    // console.log(accounts)
    
    let checkingAccounts = Object.values(accounts).filter(account => account.accountType === 'checking')
    let savingsAccounts = Object.values(accounts).filter(account => account.accountType === 'savings')
    let investingAccounts = Object.values(accounts).filter(account => account.accountType === 'investing')
    let loanAccounts = Object.values(accounts).filter(account => account.accountType === 'loan')
//    console.log(checkingAccounts)

    return(
      <div className="container mt-5 bg-white rounded">
            <h2 className="border-bottom mx-2 p-2">Accounts</h2>
            <div className="container mt">
                <h6 className="text-black-50">All Accounts</h6>
                <div className="row">
                    <div className="col border-end border-info align-self-start px-0">
                        <div className="card bg-light">
                            <div className="card-body">
                                <h5 className="card-title"> Checking</h5>
                                <h6 className="card- subtitle mb-2 text-muted">{checkingAccounts.length} Accounts</h6>
                            </div>
                        </div>
                        {accounts && checkingAccounts.map((account, i) => {
                            return(
                                <ul>
                                    <li key={i}>{account.accountName} <span>{formatter.format(account.balance)}</span></li>
                                    <div>{account.accountNumber}</div>
                                </ul>
                            );
                        })}
                        <div className="card bg-light">
                            <div className="card-body">
                                <h5 className="card-title"> Saving</h5>
                                <h6 className="card- subtitle mb-2 text-muted">{savingsAccounts.length} Accounts</h6>
                            </div>
                        </div>
                        {accounts && savingsAccounts.map((account, i) => {
                            return(
                                <ul>
                                    <li>{account.accountName} <span>{formatter.format(account.balance)}</span></li>
                                    <div>{account.accountNumber}</div>
                                </ul>
                            );
                        })}
        
                </div>
                    <div className="col-6 align-self-center">
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
                    <div className="col border-start border-info align-self-end">
                        <div className="card-body">
                                <div className="card" > 
                                    <h6 className="card-title border-bottom mb-4 bg-light text-center p-2" >Actions</h6>
                                
                                        <button className="dashboard-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Dashboard</button>
                                        <button className="accounts-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Accounts</button>
                                        <button className="transfers-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Transfers</button>
                                        <button className="loans-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75">Loans</button>
                                        <button className="new-account-btn mx-4 mb-2 text-white btn btn-primary btn-lg w-75" onClick={toRegisterAccountPage} >Open A New Account</button>
                                
                                </div>
                
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Account;