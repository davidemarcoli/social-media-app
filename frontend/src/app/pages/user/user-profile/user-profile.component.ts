import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "@services/user/user.service";
import {lastValueFrom} from "rxjs";
import {User} from "@models/user";
import {AlertService} from "@services/alert/alert.service";

@Component({
  selector: 'dl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private alertService: AlertService) {
  }

  ngOnInit() {
    console.log(this.route)

    // get username from path variable
    const username = this.route.snapshot.paramMap.get('username') || '';
    // get user profile
    const $user = this.userService.getUserByUsername(username);
    lastValueFrom($user).then(user => {
      this.user = user;
      console.log(user)
    })
      .catch(error => {
        console.error(error);
        this.alertService.error('Error while getting user profile');
      })
  }

  isAdministrator() {
    return this.user?.roles.some(role => role.name === 'ROLE_ADMIN');
  }
}
