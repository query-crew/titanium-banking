import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountListItemComponent } from './components/account-list-item/account-list-item.component';
import { AccountsPageComponent } from './components/accounts-page/accounts-page.component';
import { AccountDetailsPageComponent } from './components/account-details-page/account-details-page.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InfiniteScrollModule } from "ngx-infinite-scroll";
import { ScrollingModule } from '@angular/cdk/scrolling';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PercentPipe } from './percent.pipe';

@NgModule({
  declarations: [
    AccountsPageComponent,
    AccountListItemComponent,
    AccountDetailsPageComponent,
    PercentPipe
  ],
  exports: [
    AccountsPageComponent,
    AccountListItemComponent,
    AccountDetailsPageComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    FormsModule,
    InfiniteScrollModule,
    ScrollingModule,
    MatTooltipModule,
    BrowserAnimationsModule
  ],
})
export class AccountModule {}
