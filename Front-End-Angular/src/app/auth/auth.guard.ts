import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationRequest} from "../models/ authenticationrequest";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';
  private loggedIn = false; // Biến cục bộ để theo dõi trạng thái đăng nhập

  constructor(private http: HttpClient) {}

  authenticate(authRequest: AuthenticationRequest): Observable<{ success: boolean; message: string }> {
    return this.http.post<{ success: boolean; message: string }>(`${this.baseUrl}/login`, authRequest);
  }

  // Phương thức này xác định xem người dùng đã đăng nhập hay chưa
  isUserLoggedIn(): boolean {
    return this.loggedIn; // Trả về trạng thái đăng nhập
  }

  // Phương thức để thay đổi trạng thái đăng nhập
  setLoggedIn(status: boolean): void {
    this.loggedIn = status; // Cập nhật trạng thái đăng nhập
  }
}
