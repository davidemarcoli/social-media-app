import {Component, OnInit} from '@angular/core';
import {PostService} from "@services/post/post.service";
import {Post} from "@models/post";
import {Router} from "@angular/router";
import * as moment from "moment";
// @ts-ignore
import FuzzySearch from 'fuzzy-search';

@Component({
  selector: 'app-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.css']
})
export class ListPostsComponent implements OnInit {

  allPosts: Post[] = [];
  searchedPosts: { post: Post, importance: number }[] = [];

  constructor(private postService: PostService, private router: Router) {
    this.postService.getPosts().toPromise().then(value => {
      this.allPosts = value || [];
      this.allPosts = this.allPosts.sort((a, b) => {
        return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
      });
      console.log("All Posts", this.allPosts)
      this.searchPosts()
    })
  }

  navigateToPost(id: number) {
    this.router.navigate(['/post/view'], {queryParams: {id: id}});
  }

  getRelativeDate(date: Date) {
    return moment(date).fromNow();
  }

  // search(event?: any) {
  //   this.searchedPosts = []
  //
  //   if (event) {
  //     const searchTerm = event.target.value.toUpperCase();
  //     console.log(event)
  //
  //     if (searchTerm.length == 0) {
  //       this.search()
  //       return
  //     }
  //
  //     const newSearchedPosts: { post: Post, importance: number }[] = [];
  //
  //     this.posts.forEach(post => {
  //       const isInCategories = post.categories.filter(category => {
  //         return category.name.toUpperCase().includes(searchTerm)
  //       }).length > 0;
  //       const isInTitle = post.title.toUpperCase().includes(searchTerm)
  //       if (isInTitle) {
  //         newSearchedPosts.push({post: post, importance: 3});
  //       } else if (isInCategories) {
  //         newSearchedPosts.push({post: post, importance: 2})
  //       }
  //     })
  //
  //     console.log("Searched Posts before Fuzzy", newSearchedPosts)
  //
  //     const contentSearcher = new FuzzySearch(this.posts, ['content'], {
  //       caseSensitive: false,
  //       sort: true
  //     });
  //     const contentSearchResult = contentSearcher.search(searchTerm)
  //     console.log("Fuzzy Results", contentSearchResult)
  //
  //     contentSearchResult.forEach((searchedPost: Post) => {
  //       const isAlreadySearched = newSearchedPosts.filter(currentSearchedPost => currentSearchedPost.post.id == searchedPost.id).length > 0
  //
  //       console.log(searchedPost.title + " is already searched: " + isAlreadySearched)
  //
  //       if (!isAlreadySearched) newSearchedPosts.push({post: searchedPost, importance: 1})
  //     })
  //
  //     this.searchedPosts = newSearchedPosts;
  //
  //     this.searchedPosts.sort((a, b) => b.importance - a.importance)
  //     console.log("Final Sorted Searchposts", this.searchedPosts)
  //   } else {
  //     this.searchedPosts = this.posts.map(value => {
  //       return {post: value, importance: 3}
  //     });
  //   }
  // }

  searchPosts(event?: any) {
    console.log("All Posts in Beginning of Search", this.allPosts)

    if (event) {
      const searchTerm = event.target.value.toUpperCase();
      console.log(searchTerm)

      if (searchTerm.length == 0) {
        this.searchPosts()
        return
      }

      const newSearchedPosts: { post: Post, importance: number }[] = [];

      newSearchedPosts.push(...this.handleSpecialExpression(searchTerm))
      console.log("Searched Posts after Special Expression", newSearchedPosts)

      const titleSearchResult = this.filterPostsByTitle(searchTerm);
      const categorySearchResult = this.filterPostsByCategory(searchTerm);
      const contentSearchResult = this.filterPostsByContent(searchTerm);

      console.log("Title Search Result", titleSearchResult)
      console.log("Category Search Result", categorySearchResult)
      console.log("Content Search Result", contentSearchResult)

      titleSearchResult.forEach(value => {
          if (!this.isAlreadySearched(value.post, newSearchedPosts)) newSearchedPosts.push(value)
        }
      )
      categorySearchResult.forEach(value => {
          if (!this.isAlreadySearched(value.post, newSearchedPosts)) newSearchedPosts.push(value)
        }
      )
      contentSearchResult.forEach(value => {
          if (!this.isAlreadySearched(value.post, newSearchedPosts)) newSearchedPosts.push(value)
        }
      )

      this.searchedPosts = newSearchedPosts;
      this.searchedPosts.sort((a, b) => b.importance - a.importance)
      console.log("Final Sorted Searchposts", this.searchedPosts)
    } else {
      this.searchedPosts = this.allPosts.map(value => {
        return {post: value, importance: 3}
      });
    }
  }

  handleSpecialExpression(searchTerm: string) {
    if (searchTerm.startsWith("title:".toUpperCase())) {
      return this.filterPostsByTitle(searchTerm.replace("title:".toUpperCase(), ""))
    } else if (searchTerm.startsWith("category:".toUpperCase())) {
      return this.filterPostsByCategory(searchTerm.replace("category:".toUpperCase(), ""))
    } else if (searchTerm.startsWith("content:".toUpperCase())) {
      return this.filterPostsByContent(searchTerm.replace("content:", ""))
    } else if (searchTerm.startsWith("author:".toUpperCase()) || searchTerm.startsWith("@")) {
      return this.filterPostsByAuthor(searchTerm.replace("author:".toUpperCase(), "").replace("@", ""))
    } else {
      return []
    }
  }

  isAlreadySearched(post: Post, searchedPosts: { post: Post, importance: number }[]) {
    return searchedPosts.filter(searchedPost => searchedPost.post.id == post.id).length > 0
  }

  filterPostsByTitle(title: string) {
    title = title.toUpperCase();

    return this.allPosts.filter(post => {
      return post.title.toUpperCase().includes(title)
    }).map(value => {
      return {post: value, importance: 3}
    });
  }

  filterPostsByCategory(categoryName: string) {
    categoryName = categoryName.toUpperCase();

    return this.allPosts.filter(post => {
      return post.categories.filter(category => {
        return category.name.toUpperCase().includes(categoryName)
      }).length > 0
    }).map(value => {
      return {post: value, importance: 3}
    });
  }

  filterPostsByContent(content: string) {
    const newSearchedPosts: { post: Post, importance: number }[] = [];

    console.log("All Posts before Trick", this.allPosts)

    // strip html tags from content for better search results
    let preparedPosts: Post[] = JSON.parse(JSON.stringify(this.allPosts));
    preparedPosts.forEach(post => {
      const temporalDivElement = document.createElement("div");
      temporalDivElement.innerHTML = post.content;
      post.content = temporalDivElement.textContent || temporalDivElement.innerText || "";
    });

    const contentSearcher = new FuzzySearch(preparedPosts, ['content'], {
      caseSensitive: false,
      sort: true
    });
    const contentSearchResult = contentSearcher.search(content)

    contentSearchResult.forEach((searchedPost: Post) => {
      const originalPost = this.allPosts.filter(post => post.id == searchedPost.id)[0]
      console.log("Original Post", originalPost)
      newSearchedPosts.push({post: originalPost, importance: 1})
      // newSearchedPosts.push({post: searchedPost, importance: 1})
    })

    console.log("All Posts after Trick", this.allPosts)

    return newSearchedPosts;
  }

  filterPostsByAuthor(author: string) {
    author = author.toUpperCase();

    return this.allPosts.filter(post => {
      return post.author.username.toUpperCase().includes(author)
    }).map(value => {
      return {post: value, importance: 3}
    });
  }

  ngOnInit(): void {
  }

}
