import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuPageComponent } from './modules/admin/pages/menu-page/menu-page.component';
import { PageNotFoundPageComponent } from './modules/admin/pages/page-not-found-page/page-not-found-page.component';
import { LoginPageComponent } from './modules/auth/pages/login-page/login-page.component';
import { AddBranchPageComponent } from './modules/branch/pages/add-branch-page/add-branch-page.component';
import { BranchListPageComponent } from './modules/branch/pages/branch-list-page/branch-list-page.component';
import { BranchPageComponent } from './modules/branch/pages/branch-page/branch-page.component';
import { EditBranchPageComponent } from './modules/branch/pages/edit-branch-page/edit-branch-page.component';

//{ path: '', redirectTo: '/login', pathMatch: 'full' },
//may need to change this when auth is implemented.
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'branch', component: BranchListPageComponent },
  { path: 'branch/add', component: AddBranchPageComponent },
  { path: 'branch/view/:id', component: BranchPageComponent },
  { path: 'branch/edit/:id', component: EditBranchPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'home', component: MenuPageComponent },
  { path: '**', component: PageNotFoundPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
