import { Injectable } from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuardService {

  constructor(public auth: AuthService, public router: Router) {}

  canActivate(): boolean {
    const roles = this.auth.getRoles();

    if (roles.includes('ROLE_ADMIN')) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
