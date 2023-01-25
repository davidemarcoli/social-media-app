import { Component } from '@angular/core';
import { Post } from '~/app/models/post';

@Component({
  selector: 'dl-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent {

  allPosts: Post[] = [];

}
