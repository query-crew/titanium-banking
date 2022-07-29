import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TransactionInterface } from '../components/TransactionInterface';

@Component({
  selector: 'app-transaction-page',
  templateUrl: './transaction-page.component.html',
  styleUrls: ['./transaction-page.component.css']
})
export class TransactionPageComponent implements OnInit {

  responseObject : any;
  transactionsList : TransactionInterface[];
  filteredTransactionsList : TransactionInterface[];
  accountNumber: String;

  constructor(private http : HttpClient) {
    this.accountNumber = "";
    this.transactionsList = [];
    this.filteredTransactionsList = [];
   }

  ngOnInit(): void {
    
  }

  applyFilters(): void {

    this.filteredTransactionsList = [];

    let dateFrom = (document.getElementById("dateFrom") as HTMLInputElement).value;
    let dateTo = (document.getElementById("dateTo") as HTMLInputElement).value;
    let description = (document.getElementById("transactionDescription") as HTMLInputElement).value;
    let type = (document.getElementById("transactionType") as HTMLInputElement).value;
    let status = (document.getElementById("transactionStatus") as HTMLInputElement).value;

    console.log(Date.parse(dateFrom));
    console.log(Date.parse(dateTo));
    console.log(description);
    console.log(type);
    console.log(status);

    for(let t of this.transactionsList) {
      if( (t.transactionDate.valueOf() < Date.parse(dateTo) && t.transactionDate.valueOf() > Date.parse(dateFrom)) && t.transactionType == parseInt(type)) {
        this.filteredTransactionsList.push(t);
      }
    }

    console.log(this.transactionsList);
    console.log(this.filteredTransactionsList);
  }

  getTransactions(): void {
    this.accountNumber = (document.getElementById("accountNumberInput") as HTMLInputElement).value;
    this.http.get('http://localhost:8080/transaction/fromAccount/' + this.accountNumber).subscribe(Response => {
      if(Response) {
        this.responseObject = Response;
        this.transactionsList = this.responseObject.transactions;
        this.applyFilters();
      }
      else {
        console.log(Response);
      }
    });
  }
}