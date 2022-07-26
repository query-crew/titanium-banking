import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuPageComponent } from './pages/menu-page/menu-page.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { RouterModule } from '@angular/router';
import { PageNotFoundPageComponent } from './pages/page-not-found-page/page-not-found-page.component';

@NgModule({
  declarations: [MenuPageComponent, PageNotFoundPageComponent],
  exports: [MenuPageComponent, PageNotFoundPageComponent],
  imports: [CommonModule, AppRoutingModule, RouterModule],
})
export class AdminModule {}
