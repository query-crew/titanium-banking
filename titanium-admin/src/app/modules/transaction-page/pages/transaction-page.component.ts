import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { TransactionInterface } from '../components/TransactionInterface';

declare let window: any;

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
  showCreateTransactionModal: boolean;
  createTransactionModal : any;

  constructor(private http : HttpClient) {
    this.accountNumber = "";
    this.transactionsList = [];
    this.filteredTransactionsList = [];
    this.showCreateTransactionModal = false;
   }

  ngOnInit(): void {
    this.initModal();
  }

  initModal() : void {
    this.createTransactionModal = new window.bootstrap.Modal(document.getElementById('createTransactionModal'));
  }

  applyFilters(): void {

    this.filteredTransactionsList = [];

    let dateFrom = (document.getElementById("dateFrom") as HTMLInputElement).value;
    let dateTo = (document.getElementById("dateTo") as HTMLInputElement).value;
    let description = (document.getElementById("transactionDescription") as HTMLInputElement).value;
    let type = (document.getElementById("transactionType") as HTMLInputElement).value;
    let status = (document.getElementById("transactionStatus") as HTMLInputElement).value;

    for(let t of this.transactionsList) {
      if( (t.transactionDate.valueOf() < Date.parse(dateTo) && t.transactionDate.valueOf() > Date.parse(dateFrom)) && t.transactionType == parseInt(type)) {
        this.filteredTransactionsList.push(t);
      }
    }
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

  toggleShowCreateTransactionModal(): void {
    this.showCreateTransactionModal = !this.showCreateTransactionModal;
    if(this.showCreateTransactionModal) {
      this.createTransactionModal.show();
    }
    else {
      this.createTransactionModal.hide();
    }
  }

  postTransactionData(): void {

    let date = (document.getElementById('modalTransactionDate') as HTMLInputElement).value;
    let fromAccount = (document.getElementById('modalFromAccount') as HTMLInputElement).value;
    let toAccount = (document.getElementById('modalToAccount') as HTMLInputElement).value;
    let amount = (document.getElementById('modalTransactionAmount') as HTMLInputElement).value;
    let description = (document.getElementById('modalTransactionDescription') as HTMLInputElement).value;
    let type = (document.getElementById('modalTransactionType') as HTMLInputElement).value;

    this.http.post<any>('http://localhost:8080/transaction', {
      "transactionType": Number.parseInt(type),
      "transactionDate": Date.parse(date),
      "description": description,
      "amount": (Number.parseFloat(amount) * 100).toFixed(0),
      "accountFromId": Number.parseInt(fromAccount),
      "accountToId": Number.parseInt(toAccount)
    }).subscribe(Response => {
      console.log(Response);
    });
  }
}