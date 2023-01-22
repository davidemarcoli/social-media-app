import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "@services/user/user.service";
import {lastValueFrom} from "rxjs";
import {User} from "@models/user";
import {AlertService} from "@services/alert/alert.service";
import {Post} from "@models/post";
import {PostService} from "@services/post/post.service";
import * as moment from "moment";
import {DateUtil} from "@utils/date.util";

@Component({
  selector: 'dl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;
  posts: Post[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private alertService: AlertService, private postService: PostService) {
  }

  ngOnInit() {
    console.log(this.route)

    // get username from path variable
    this.route.paramMap.subscribe(value => {
      const username = value.get('username');
      if (username) {
        lastValueFrom(this.userService.getUserByUsername(username)).then(user => {
          this.user = user;
          lastValueFrom(this.postService.getPostsByUser(user.username)).then(posts => {
            this.posts = posts || [];
          }).catch(error => {
            console.error(error);
            this.alertService.error(error.error.message);
          })
        }).catch(error => {
          console.error(error);
          this.alertService.error(error.error.message);
          // this.alertService.error('Error while getting user profile');
        });
      }
    });
  }

  onPostDoubleClick(post: Post) {
    console.log('double click', post);
  }

  getRelativeDate(date: Date) {
    return DateUtil.getRelativeDate(date);
  }

  isAdministrator() {
    return this.user?.roles.some(role => role.name === 'ROLE_ADMIN');
  }
}
