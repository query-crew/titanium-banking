import { Component, Input, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import { AccountType } from '../../models/account-type';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from '../../services/account.service';
import { Transaction } from '../../models/transaction';

@Component({
  selector: 'app-account-details-page',
  templateUrl: './account-details-page.component.html',
  styleUrls: ['./account-details-page.component.css']
})
export class AccountDetailsPageComponent implements OnInit {
  accountId: any;
  isLoading: boolean = true;
  account: Account = {
    accountId: 0,
    accountNumber: "",
    balance: 0,
    lastStatementDate: "",
    enabled: 0,
    memberId: 0,
    accountTypeId: 0
  }
  accountType: AccountType = {
    accountTypeId: 0,
    accountType: "",
    accountTypeAbbr: "",
    accountColor: "",
    interest: 0,
    balanceRequirement: 0,
    loanId: 0
  }

  colorIsEmpty: boolean = true;

  transactions: Transaction[] = [];

  months: String[] = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
  constructor(private accountService: AccountService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAccount()
    this.colorIsNotEmpty();
  }

  private colorIsNotEmpty() {
    if (this.accountType.accountColor !== "") {
      this.colorIsEmpty = false;
    }
  }

  private getAccount() {
    this.accountId = this.activatedRoute.snapshot.paramMap.get('id');
    this.accountService.getAccountById(this.accountId).subscribe({
      next: (res) => {
        this.account = res.account;
        this.getAccountType();
        this.getTransactions();
        this.isLoading = false;
      },
      error: (e) => {
        console.log(e);
      }
    })
  }

  private getAccountType() {
    this.accountService.getAccountTypeById(this.account.accountTypeId).subscribe({
      next: (res) => {
        this.accountType = res.accountType;
      },
      error: (e) => {
        console.log(e);
      }
    })
  }

  private getTransactions(): void {
    this.accountService.getTransactionsFromAccountId(this.account.accountId).subscribe({
      next: (res) => {
        this.transactions = res.transactions;
        this.transactions = this.transactions.map((transaction) => {
          transaction.transactionDate = new Date(transaction.transactionDate);
          return transaction;
        })
      },
      error: (e) => {
        console.log(e);
      }
    })
  }
}
