import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Account } from '../models/account';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AccountService {

  constructor(private http: HttpClient) { }

  getAccounts(pageNo: number, pageSize: number): Observable<Account[]> {
    const accountUrl = "https://localhost:8444/accounts/?pageNo=" + pageNo + "&pageSize=" + pageSize;
    return this.http.get<Account[]>(accountUrl, { withCredentials: true });
  }

  removePaddingZeros(paddedNum: string): string {
    const regex: RegExp  = new RegExp("^0+(?!$)",'g');
    const retNum = paddedNum.replaceAll(regex, "");
    return retNum;
  }

  titleCase(str: string): string {
    let strArr: string[] = str.toLowerCase().split(' ');
    for (var i = 0; i < strArr.length; i++) {
      strArr[i] = strArr[i].charAt(0).toUpperCase() + strArr[i].slice(1); 
    }
    return strArr.join(' ');
  }
}