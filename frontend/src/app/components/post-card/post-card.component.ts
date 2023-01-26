import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { Post } from '~/app/models/post';

@Component({
  selector: 'dl-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent {

  @Input() post: Post|undefined;

  ngOnInit(): void {
  }
}
