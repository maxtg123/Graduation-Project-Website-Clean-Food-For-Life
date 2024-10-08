import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationRequest } from '../models/registration-request';
import {AuthenticationRequest} from "../models/ authenticationrequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth'; // Cần thay đổi đường dẫn khi cần

  constructor(private http: HttpClient) {}

  authenticate(body: AuthenticationRequest): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.baseUrl}/authenticate`, body);
  }

  login(authRequest: AuthenticationRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, authRequest);
  }

  register(request: RegistrationRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, request);
  }
  activateAccount(request: { code: string }): Observable<any> {
    // Gửi mã kích hoạt đến API
    return this.http.post(`${this.baseUrl}/activate-account`, request);
  }
}
