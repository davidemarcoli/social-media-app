import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "@services/post/post.service";
import {Post} from "@models/post";
import {DomSanitizer} from "@angular/platform-browser";
import {User} from "@models/user";
import {AuthService} from "@services/auth/auth.service";
import {DateUtil} from "@utils/date.util";

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent implements OnInit {

  post: Post = new Post(0, "", new User("", "", "", "", "", [], [], [], [], []), new Date(), new Date(), "", []);

  constructor(private route: ActivatedRoute, private router: Router, private postService: PostService, private sanitizer: DomSanitizer, private authService: AuthService) {
    this.route.queryParams.subscribe(params => {
      this.postService.getPostById(params['id']).toPromise().then(value => {
        this.post = value!;
      });
    });
  }

  ngOnInit(): void {

  }

  deletePost() {
    if (!this.canModify()) {
      alert("You do not have permission to delete this post");
      return;
    }
    if (confirm("Are you sure you want to delete this post?")) {
      console.log("Deleting post");
      this.postService.deletePost(this.post.id).toPromise().then(() => {
        this.router.navigate(['/']);
      });
    }
  }

  getRelativeDate(date: Date) {
    return DateUtil.getRelativeDate(date);
  }

  getFormattedDate(date: Date) {
    return DateUtil.getFormattedDate(date);
  }

  getPostContent() {
    return this.sanitizer.bypassSecurityTrustHtml(this.post.content);
  }

  canModify() {
    return this.post.id !== 0 && (this.isAuthor() || (this.isModerator() && !this.isAdminPost()));
  }

  isAuthor() {
    console.log(this.authService.getUsername() + " " + this.post.author.username)
    return this.authService.getUsername() == this.post.author.username;
  }

  isAdminPost() {
    console.log(this.post.author.roles.filter(value => value.name == "ROLE_ADMIN").length > 0)
    return this.post.author.roles.filter(value => value.name == "ROLE_ADMIN").length > 0;
  }

  isModerator() {
    console.log(this.authService.getRoles().filter((value: string) => value == "ROLE_MODERATOR").length > 0)
    return this.authService.getRoles().filter((value: string) => value == "ROLE_MODERATOR").length > 0;
  }

}
