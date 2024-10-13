import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  // Có thể loại bỏ hoàn toàn TokenService nếu không sử dụng token nữa
  // Nếu cần thiết lập trạng thái người dùng đã đăng nhập, bạn có thể giữ lại phương thức này

  // Kiểm tra xem người dùng đã đăng nhập chưa
  isAuthenticated(): boolean {
    // Bạn có thể sử dụng một biến flag khác để quản lý trạng thái đăng nhập
    return !!localStorage.getItem('isLoggedIn');
  }

  // Đặt trạng thái đăng nhập
  setAuthenticated(isLoggedIn: boolean): void {
    if (isLoggedIn) {
      localStorage.setItem('isLoggedIn', 'true');
    } else {
      localStorage.removeItem('isLoggedIn');
    }
  }

  // Xoá trạng thái khi đăng xuất
  clearAuth(): void {
    this.setAuthenticated(false);
  }
}
