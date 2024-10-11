import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {RegisterComponent} from "./pages/register/register.component";
import {ActivateAccountComponent} from "./pages/activate-account/activate-account.component";
import {IndexComponent} from "./module/index/index.component";

const routes: Routes = [

  {
    path: '',
    redirectTo: 'index',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'activate-account',
    component: ActivateAccountComponent
  },
  {
    path: 'index',
    component: IndexComponent
  }


  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
