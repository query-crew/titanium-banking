import { Component, Input, OnInit } from '@angular/core';
import { Account } from '../../models/account';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-account-list-item',
  templateUrl: './account-list-item.component.html',
  styleUrls: ['./account-list-item.component.css']
})
export class AccountListItemComponent implements OnInit {

  constructor(private accountService: AccountService) { }

  @Input() account!: Account;
  hasMember: boolean = true;

  ngOnInit(): void {
    this.setHasMember();
  }

  setHasMember(): void {
    if (this.account.memberId === 0) {
      this.hasMember = false;
    }
  }
}
