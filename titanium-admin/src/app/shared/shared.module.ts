import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { InternalServerErrorPageComponent } from './pages/internal-server-error-page/internal-server-error-page.component';
import { UnauthorizedPageComponent } from './pages/unauthorized-page/unauthorized-page.component';

@NgModule({
  declarations: [NavbarComponent, NotFoundPageComponent, InternalServerErrorPageComponent, UnauthorizedPageComponent],
  exports: [NavbarComponent],
  imports: [CommonModule, AppRoutingModule],
})
export class SharedModule {}
