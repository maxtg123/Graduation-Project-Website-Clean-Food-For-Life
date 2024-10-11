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

  // Cập nhật phương thức authenticate để không trả về token nữa
  authenticate(body: AuthenticationRequest): Observable<{ success: boolean, message: string }> {
    return this.http.post<{ success: boolean, message: string }>(`${this.baseUrl}/login`, body); // Đổi thành login
  }

  login(authRequest: AuthenticationRequest): Observable<{ success: boolean, message: string }> {
    // Phương thức này có thể không cần nữa vì authenticate đã thay thế
    return this.authenticate(authRequest);
  }

  register(request: RegistrationRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, request);
  }

  activateAccount(request: { code: string }): Observable<any> {
    // Gửi mã kích hoạt đến API
    return this.http.post(`${this.baseUrl}/activate-account`, request);
  }
}
