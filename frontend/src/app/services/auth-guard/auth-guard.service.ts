import { Injectable } from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(public auth: AuthService, public router: Router) {}
  canActivate(): boolean {
    const user = localStorage.getItem('currentUser');
    // if path is login or signup, then allow
    if (this.router.url === '/login' || this.router.url === '/signup') {
      return true;
    }
    console.log(user)
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
