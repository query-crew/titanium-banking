import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { InternalServerErrorPageComponent } from './pages/internal-server-error-page/internal-server-error-page.component';

@NgModule({
  declarations: [NavbarComponent, NotFoundPageComponent, InternalServerErrorPageComponent],
  exports: [NavbarComponent],
  imports: [CommonModule, AppRoutingModule],
})
export class SharedModule {}
