import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Account } from '../models/account';
import { Member } from '../models/member';
import { AccountType } from '../models/account-type';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AccountService {

  constructor(private http: HttpClient) { }

  getAccounts(pageNo: number, pageSize: number): Observable<any> {
    const accountUrl = environment.accountApiPath + "/account/?pageNo=" + pageNo + "&pageSize=" + pageSize;
    return this.http.get<any>(accountUrl, { withCredentials: true });
  }

  getAccountTypeById(accountTypeId: number): Observable<any> {
    const accountTypeUrl = environment.accountApiPath + "/accountType/" + accountTypeId;
    return this.http.get<any>(accountTypeUrl, { withCredentials: true});
  }

  getAccountTypes(): Observable<any> {
    const accountTypeUrl = environment.accountApiPath + "/accountType";
    return this.http.get<any>(accountTypeUrl, { withCredentials: true});
  }

  getMemberById(memberId: number): Observable<any> {
    const memberUrl = environment.userApiPath + "/member/" + memberId;
    return this.http.get<any>(memberUrl, { withCredentials: true });
  }

  getMembers(): Observable<any> {
    const memberUrl = environment.userApiPath + "/member";
    return this.http.get<any>(memberUrl, { withCredentials: true });
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