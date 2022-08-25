import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminModule } from './modules/admin/admin.module';
import { AuthModule } from './modules/auth/auth.module';
import { BranchModule } from './modules/branch/branch.module';
import { TransactionPageModule } from './modules/transaction-page/transaction-page.module';
import { SharedModule } from './shared/shared.module';
import { TransactionPageModule } from './modules/transaction-page/transaction-page.module';
import { AuthGuard } from './modules/auth/services/auth-guard.service';
import { AccountModule } from './modules/account/account.module';

@NgModule({
  declarations: [AppComponent, ],
  //if you have issues with routing not registering... add module import here
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    BranchModule,
    SharedModule,
    AdminModule,
    TransactionPageModule,
    AccountModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
