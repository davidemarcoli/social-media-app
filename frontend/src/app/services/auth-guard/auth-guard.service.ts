import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(): boolean {
    const user = localStorage.getItem('currentUser');
    if (!user) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
