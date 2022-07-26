import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Branch } from '../models/branch';

const apiUrl = 'http://localhost:8080/branch';
@Injectable({
  providedIn: 'root',
})
export class BranchService {
  constructor(private http: HttpClient) {}
  getBranch(branchId: number): Observable<Branch> {
    return this.http.get<any>(apiUrl + '/' + branchId).pipe(
      map((res) => {
        return <Branch>res.branch;
      })
    );
  }

  getBranches(): Observable<any> {
    return this.http.get<any>(apiUrl);
  }
}
