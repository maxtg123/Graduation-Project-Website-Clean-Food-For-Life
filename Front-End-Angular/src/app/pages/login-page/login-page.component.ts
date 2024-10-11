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

  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenService: TokenService
  ) {}

  login() {
    this.errorMsg = [];
    this.authService.authenticate(this.authRequest).subscribe({
      next: (res: { token: string }) => {
        // Lưu token
        this.tokenService.token = res.token;
        this.router.navigate(['index']);
      },
      error: (err: any) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    });
  }
  register() {
    this.router.navigate(['register']); // Chuyển hướng đến trang đăng ký
  }
}
