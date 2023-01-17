import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "@services/user/user.service";
import {lastValueFrom} from "rxjs";
import {User} from "@models/user";
import {AlertService} from "@services/alert/alert.service";
import {Post} from "@models/post";

@Component({
  selector: 'dl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;
  posts: Post[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private alertService: AlertService) {
  }

  ngOnInit() {
    console.log(this.route)

    // get username from path variable
    this.route.paramMap.subscribe(value => {
      const username = value.get('username');
      if (username) {
        lastValueFrom(this.userService.getUserByUsername(username)).then(user => {
          this.user = user;
        }).catch(error => {
          console.error(error);
          this.alertService.error(error.error.message);
          // this.alertService.error('Error while getting user profile');
        });
      }
    });
  }

  isAdministrator() {
    return this.user?.roles.some(role => role.name === 'ROLE_ADMIN');
  }

  bla() {
    prompt("Please enter the new profile picture URL:", "Harry Potter");
  }
}
