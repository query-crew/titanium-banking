import { HttpEventType } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Obj } from '@popperjs/core';
import { map } from 'rxjs';
import { Account } from '../../models/account';
import { AccountType } from '../../models/account-type';
import { Member } from '../../models/member';
import { AccountService } from '../../services/account.service';
import { AccountListItemComponent } from '../account-list-item/account-list-item.component';

@Component({
  selector: 'app-accounts-page',
  templateUrl: './accounts-page.component.html',
  styleUrls: ['./accounts-page.component.css']
})
export class AccountsPageComponent implements OnInit {

  isLoading = true;
  accounts: Account[] = [];
  accountTypes: AccountType[] = [];
  members: Member[] = [];
  page: number = 0;
  pageSize: number = 10;

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.getAccounts();
  }

  private getAccounts(): void {
    this.accountService.getAccounts(this.page, this.pageSize).subscribe({
      next: (res) => {
        this.accounts = res.accounts;
        this.accounts = this.accounts.map((account) => {
          account.accountNumber = this.accountService.removePaddingZeros(account.accountNumber);
          return account;
        });
        this.getAccountTypes();
        this.getMembers();
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  private getAccountTypes(): void {
    this.accountService.getAccountTypes().subscribe({
      next: (res) => {
        this.accountTypes = res.accountTypes;
        this.accountTypes = this.accountTypes.map((accountType) => {
          accountType.accountType = this.accountService.titleCase(accountType.accountType);
          return accountType;
        });
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  private getMembers(): void {
    this.accountService.getMembers().subscribe({
      next: (res) => {
        this.members = res.members;
        this.members = this.members.map((member) => {
          member.firstName = this.accountService.titleCase(member.firstName);
          member.lastName = this.accountService.titleCase(member.lastName);
          return member;
        });
        this.isLoading = false;
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  onScroll(): void {
    this.page++;
    this.accountService.getAccounts(this.page, this.pageSize).subscribe({
      next: (res) => {
        let formattedAccounts: Account[] = res.accounts;
        formattedAccounts = formattedAccounts.map((account) => {
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
