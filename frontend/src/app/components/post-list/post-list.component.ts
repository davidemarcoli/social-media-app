import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '~/app/models/post';
import { PostService } from '~/app/services/post/post.service';

@Component({
  selector: 'dl-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent {

  allPosts: Post[] = [];

  constructor(private postService: PostService, private router: Router) {
    this.postService.getPosts().toPromise().then(value => {
      this.allPosts = value || [];
    })
  }

  ngOnInit(): void {
  }

}
