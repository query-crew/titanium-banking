import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuPageComponent } from './modules/admin/pages/menu-page/menu-page.component';
import { LoginPageComponent } from './modules/auth/pages/login-page/login-page.component';
import { AddBranchPageComponent } from './modules/branch/pages/add-branch-page/add-branch-page.component';
import { BranchListPageComponent } from './modules/branch/pages/branch-list-page/branch-list-page.component';
import { BranchPageComponent } from './modules/branch/pages/branch-page/branch-page.component';
import { EditBranchPageComponent } from './modules/branch/pages/edit-branch-page/edit-branch-page.component';
import { TransactionPageComponent } from './modules/transaction-page/pages/transaction-page.component';
import { InternalServerErrorPageComponent } from './shared/pages/internal-server-error-page/internal-server-error-page.component';
import { NotFoundPageComponent } from './shared/pages/not-found-page/not-found-page.component';
import { AuthGuard } from './modules/auth/services/auth-guard.service';
import { AccountsPageComponent } from './modules/account/components/accounts-page/accounts-page.component';

//{ path: '', redirectTo: '/login', pathMatch: 'full' },
//may need to change this when auth is implemented.
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'branch', component: BranchListPageComponent, canActivate: [AuthGuard] },
  { path: 'branch/add', component: AddBranchPageComponent, canActivate: [AuthGuard] },
  { path: 'branch/view/:id', component: BranchPageComponent, canActivate: [AuthGuard] },
  { path: 'branch/edit/:id', component: EditBranchPageComponent, canActivate: [AuthGuard] },
  { path: 'transactions', component: TransactionPageComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountsPageComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginPageComponent },
  { path: 'home', component: MenuPageComponent, canActivate: [AuthGuard]},
  { path: '401', component: NotFoundPageComponent },
  { path: '404', component: NotFoundPageComponent },
  { path: '500', component: InternalServerErrorPageComponent },
  { path: '**', component: NotFoundPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
