import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuPageComponent } from './pages/menu-page/menu-page.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [MenuPageComponent],
  exports: [MenuPageComponent],
  imports: [CommonModule, AppRoutingModule, RouterModule],
})
export class AdminModule {}
