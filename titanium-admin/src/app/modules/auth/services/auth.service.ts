import { Injectable, resolveForwardRef } from '@angular/core';
import { HttpClient, HttpParamsOptions } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Auth } from '../models/auth';
import { Router } from '@angular/router';
import { Text } from '@angular/compiler';
import { environment } from 'src/environments/environment';

const loginUrl = environment.userApiPath + '/user/login';
const authUrl = environment.userApiPath + '/user/authorize';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  private loginRequest(auth: Auth): Observable<Auth> {
    return this.http.post<Auth>(loginUrl, auth, { withCredentials: true });
  }

  navigateToHome(auth: Auth) {
    this.loginRequest(auth).subscribe({
      next: () => {
        console.log("navigating to home");
        this.router.navigate(['/home']);
      },
      error: (e) => {
        console.log(e);
        this.router.navigate(['/500']);
      },
    });
  }

  private authorizeRequest(): Observable<string> {

    const options: Object = {
      responseType: 'text' as const,
      withCredentials: true as const
    };

    return this.http.get<string>(authUrl, options);
  }

  authorize(): Promise<boolean> {
    return new Promise((resolve) => {
      this.authorizeRequest().subscribe({
        next: (res) => {
          if (res === "admin") {
            resolve(true);
          }
          else {
            this.router.navigate(['/401']);
            resolve(false);
          }
        },
        error: (e) => {
          if (e.status === 401) {
            this.router.navigate(['/401']);
            resolve(false);
          }
          else {
            console.log(e);
            this.router.navigate(['/500']);
            resolve(false);
          }
        }
      });
    });
  }
}
