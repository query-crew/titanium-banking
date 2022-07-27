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
    this.filteredTransactionsList = this.transactionsList;
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