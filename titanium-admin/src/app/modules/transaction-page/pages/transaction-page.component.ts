import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transaction-page',
  templateUrl: './transaction-page.component.html',
  styleUrls: ['./transaction-page.component.css']
})
export class TransactionPageComponent implements OnInit {

  responseObject : any;
  transactionsList = [];
  filteredTransactionsList = [];
  accountNumber: String;

  constructor(private http : HttpClient) {
    this.accountNumber = "";
   }

  ngOnInit(): void {
    
  }

  applyFilters(): void {
    console.log("apply filters");
  }

  getTransactions(): void {
    this.accountNumber = (document.getElementById("accountNumberInput") as HTMLInputElement).value;
    this.http.get('http://localhost:8080/transaction/fromAccount/' + this.accountNumber).subscribe(Response => {
      if(Response) {
        this.responseObject = Response;
        this.filteredTransactionsList = this.responseObject.transactions;
      }
      else {
        console.log(Response);
      }
    });
    this.applyFilters();
  }
}