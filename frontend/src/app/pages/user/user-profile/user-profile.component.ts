import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "@services/user/user.service";
import {lastValueFrom} from "rxjs";
import {User} from "@models/user";
import {AlertService} from "@services/alert/alert.service";
import {Post} from "@models/post";
import * as yup from 'yup';
import {PostService} from "@services/post/post.service";
import {DateUtil} from "@utils/date.util";
import {faHeart as OutlinedHeart} from "@fortawesome/free-regular-svg-icons";
import {faHeart as SolidHeart} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "@services/auth/auth.service";

export const userSchema = yup.object({
  url: yup.string().url(),
});

@Component({
  selector: 'dl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;
  posts: Post[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private alertService: AlertService, private postService: PostService, private authService: AuthService) {
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

  onFollowClick() {
    lastValueFrom(this.userService.toggleFollow(this.user!)).then(user => {
      this.user = user;
      this.alertService.success('Followed user');
    }).catch(error => {
      console.error(error);
      this.alertService.error(error.error.message);
    });
  }

  isOnOwnProfile() {
    return this.user?.username === this.authService.getUsername();
  }

  isFollowingUser() {
    return this.user?.followers.some(follower => follower.username === this.authService.getUsername());
  }

  getRelativeDate(date: Date) {
    return DateUtil.getRelativeDate(date);
  }

  changeProfilePicture(oldProfilePictureURL: string) {
    let newProfilePictureURL = prompt("Please enter the new profile picture URL:", oldProfilePictureURL);
    if (newProfilePictureURL) {
      userSchema.validate({url: newProfilePictureURL}).then(() => {
        this.user!.profilePictureURL = newProfilePictureURL!;
        const user$ = this.userService.updateProfilePicture(this.user!);
        lastValueFrom(user$).then(user => {
          this.user = user;
          this.alertService.success('Profile picture updated successfully');
        }).catch(error => {
          console.error(error);
          this.alertService.error(error.error.message);
        });
      }).catch(error => {
        console.error(error);
        this.alertService.error(error.errors[0]);
      });
    } else {
      this.alertService.error('Invalid profile picture URL');
    }
  }

}
