import {Component, OnInit} from '@angular/core';
import {PostService} from "@services/post/post.service";
import {Post} from "@models/post";
import {Router} from "@angular/router";
import * as moment from "moment";
// @ts-ignore
import FuzzySearch from 'fuzzy-search';
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.css']
})
export class ListPostsComponent implements OnInit {

  allPosts: Post[] = [];
  searchedPosts: Post[] = [];

  constructor(private postService: PostService, private router: Router) {
    const posts$ = this.postService.getPosts();
    lastValueFrom(posts$).then(value => {
      this.allPosts = value || [];
      this.allPosts = this.allPosts.sort((a, b) => {
        return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
      });
      this.searchedPosts = this.allPosts;
      console.log("All Posts", this.allPosts)
    })
  }

  navigateToPost(id: number) {
    this.router.navigate(['/post/view'], {queryParams: {id: id}});
  }

  getRelativeDate(date: Date) {
    return moment(date).fromNow();
  }

  searchPosts(event?: any) {
    const searchTerm = event.target.value.toLowerCase();
    const searchedPosts$ = this.postService.searchPosts(searchTerm);
    lastValueFrom(searchedPosts$).then(value => {
      this.searchedPosts = value || [];
    })
  }

  handleSpecialExpression(searchTerm: string) {
   if (searchTerm.startsWith("content:".toUpperCase())) {
     return this.allPosts.filter(post => post.content.toLowerCase().includes(searchTerm.substring(8)))
    } else if (searchTerm.startsWith("author:".toUpperCase()) || searchTerm.startsWith("@")) {
      return this.allPosts.filter(post => post.author.username.toLowerCase().includes(searchTerm.substring(7)))
    } else {
      return []
    }
  }

  ngOnInit(): void {
  }

}
