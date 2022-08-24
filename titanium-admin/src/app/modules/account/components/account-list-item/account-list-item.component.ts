import { Component, Input, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import { AccountService } from '../../services/account.service';
import { Member } from '../../models/member';
import { AccountType } from '../../models/account-type';

@Component({
  selector: 'app-account-list-item',
  templateUrl: './account-list-item.component.html',
  styleUrls: ['./account-list-item.component.css']
})
export class AccountListItemComponent implements OnInit {

  member: Member = {
    memberId: 0,
    firstName: "",
    lastName: "",
    phone: "",
    dateOfBirth: "",
    socialSecurityNumber: "",
    memberAddress: {}
  };

  accountType: AccountType = {
    accountTypeId: 0,
    accountType: "",
    accountTypeAbbr: "",
    interest: 0,
    balanceRequirement: 0,
    loanId: 0
  }

  constructor(private accountService: AccountService) { }

  @Input() account!: Account;
  hasMember: boolean = true;

  ngOnInit(): void {
    this.setHasMember();
    if (this.hasMember) {
      this.getMember();
      this.getAccountType();
    }
  }

  private setHasMember(): void {
    if (this.account.memberId === 0) {
      this.hasMember = false;
    }
  }

  private getMember(): void {
    this.accountService.getMemberById(this.account.memberId).subscribe({
      next: (res) => {
        this.member = res.member;
      },
      error: (e) => {
        console.log(e);
      }
    })
  }

  private getAccountType(): void {
    this.accountService.getAccountTypeById(this.account.accountTypeId).subscribe({
      next: (res) => {
        this.accountType = res.accountType;
      },
      error: (e) => {
        console.log(e);
      }
    })
  }
}
