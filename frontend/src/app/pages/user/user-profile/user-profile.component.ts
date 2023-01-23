import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "@services/user/user.service";
import {lastValueFrom} from "rxjs";
import {User} from "@models/user";
import {AlertService} from "@services/alert/alert.service";
import {Post} from "@models/post";
import * as yup from 'yup';
import {PostService} from "@services/post/post.service";
import * as moment from "moment";
import {DateUtil} from "@utils/date.util";
import {faHeart} from "@fortawesome/free-solid-svg-icons";
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

  user: User = new User("", "", "", "", "", [], [], []);
  posts: Post[] = [];

  readonly faHeart = faHeart;

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

  onLikeClick(post: Post) {
    console.log('double click', post);
    lastValueFrom(this.postService.toggleLike(post)).then(post => {
      this.posts = this.posts.map(p => p.id === post.id ? post : p);
      console.log('post liked', post);
      console.log(post.likes)
      this.alertService.success('Post liked');
    }).catch(error => {
      console.error(error);
      this.alertService.error(error.error.message);
    });
  }

  hasCurrentUserLikedPost(post: Post) {
    if (post.likes)
      return post.likes.some(like => like.username === this.authService.getUsername());

    return false;
  }

  getRelativeDate(date: Date) {
    return DateUtil.getRelativeDate(date);
  }

  isAdministrator() {
    return this.user?.roles.some(role => role.name === 'ROLE_ADMIN');
  }

  changeProfilePicture(oldProfilePictureURL: string) {
    let newProfilePictureURL = prompt("Please enter the new profile picture URL:", oldProfilePictureURL);
    if (newProfilePictureURL) {
      userSchema.validate({url: newProfilePictureURL}).then(() => {
        this.user.profilePictureURL = newProfilePictureURL!;
        const user$ = this.userService.updateProfilePicture(this.user);
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
