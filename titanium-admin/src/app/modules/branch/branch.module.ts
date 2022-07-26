import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BranchPageComponent } from './pages/branch-page/branch-page.component';
import { BranchListPageComponent } from './pages/branch-list-page/branch-list-page.component';
import { BranchComponent } from './components/branch/branch.component';
import { BranchListComponent } from './components/branch-list/branch-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AddBranchPageComponent } from './pages/add-branch-page/add-branch-page.component';
import { EditBranchPageComponent } from './pages/edit-branch-page/edit-branch-page.component';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  declarations: [
    BranchPageComponent,
    BranchListPageComponent,
    BranchComponent,
    BranchListComponent,
    AddBranchPageComponent,
    EditBranchPageComponent,
  ],
  exports: [
    BranchPageComponent,
    BranchListPageComponent,
    AddBranchPageComponent,
    EditBranchPageComponent,
  ],
  imports: [CommonModule, HttpClientModule, AppRoutingModule],
})
export class BranchModule {}
