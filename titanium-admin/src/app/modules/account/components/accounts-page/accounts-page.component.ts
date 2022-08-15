import { HttpEventType } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Obj } from '@popperjs/core';
import { map } from 'rxjs';
import { Account } from '../../models/account';
import { AccountService } from '../../services/account.service';
import { AccountListItemComponent } from '../account-list-item/account-list-item.component';

@Component({
  selector: 'app-accounts-page',
  templateUrl: './accounts-page.component.html',
  styleUrls: ['./accounts-page.component.css']
})
export class AccountsPageComponent implements OnInit {

  isLoading = true;
  ownersLoading = true;
  accounts: Account[] = [];
  accountNames: string[] = [];
  accountCheckedNames: string[] = [];
  page: number = 0;
  pageSize: number = 10;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.getAccounts();
  }

  private getAccounts(): void {
    this.accountService.getAccounts(this.page, this.pageSize).subscribe({
      next: (res) => {
        this.accounts = res.map((account) => {
          account.accountName = this.accountService.titleCase(account.accountName);
          account.accountNumber = this.accountService.removePaddingZeros(account.accountNumber);
          return account;
        });

        this.populateAccountNames();
        this.isLoading = false;
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  private populateAccountNames(): void {
    for (let i = 0; i < this.accounts.length; i++) {
      if (!this.accountNames.includes(this.accounts[i].accountName)) {
        this.accountNames.push(this.accounts[i].accountName);
      }
    }
  }

  onScroll(): void {
    this.page++;
    this.accountService.getAccounts(this.page, this.pageSize).subscribe({
      next: (res: Account[]) => {
        const formattedAccounts: Account[] = res.map((account) => {
          account.accountName = this.accountService.titleCase(account.accountName);
          account.accountNumber = this.accountService.removePaddingZeros(account.accountNumber);
          return account;
        })
        this.accounts.push(...formattedAccounts);
      },
      error: (e) => {
        console.log(e);
      }
    });
  }
}
