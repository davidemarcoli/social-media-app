import { Component, OnInit } from '@angular/core'
import { AuthService } from '@services/auth/auth.service'
import { UserService } from './services/user/user.service'
import { lastValueFrom } from 'rxjs'
import { User } from './models/user'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  user: User | undefined

  constructor(
    public authService: AuthService,
    public userService: UserService,
  ) {}

  ngOnInit() {
    const $user = this.userService.getUserByUsername(
      this.authService.getUsername(),
    )
    lastValueFrom($user)
      .then(user => {
        this.user = user
        console.log(user)
      })
      .catch(error => {
        console.error(error)
        // this.alertService.error('Error while getting user profile');
        // TODO: toast-error('Error while getting user profile')
      })
  }
}
