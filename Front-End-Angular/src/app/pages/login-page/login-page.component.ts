import {Component, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../token/token.service';
import {AuthenticationRequest} from "../../models/ authenticationrequest";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginPageComponent {
  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];
  successMsg: string | null = null;

  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenService: TokenService
  ) {}

  login() {
    this.errorMsg = [];
    this.successMsg = null; // Reset thông báo thành công

    this.authService.login(this.authRequest).subscribe({
      next: (res: { success: boolean, message: string }) => {
        if (res.success) {
          this.successMsg = res.message; // Lưu thông báo thành công
          // Không cần xử lý token, chỉ hiển thị thông báo thành công
          this.router.navigate(['index']); // Có thể chuyển hướng tới trang chính nếu cần
        } else {
          this.errorMsg.push(res.message); // Thêm thông báo lỗi nếu không thành công
        }
      },
      error: (err: any) => {
        console.log(err);
        this.errorMsg.push(err.error.message || 'An error occurred.'); // Thông báo lỗi từ server
      }
    });
  }

  register() {
    this.router.navigate(['register']); // Chuyển hướng đến trang đăng ký
  }
}
