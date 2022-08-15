import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Auth } from '../../models/auth';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private authService: AuthService) { }

  auth: Auth = { 
    username: '',
    password: ''
   }

  ngOnInit(): void {
  }

  onSubmit() {
    this.authService.navigateToHome(this.auth);
  }
}
