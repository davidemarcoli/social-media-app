import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "@models/post";
import {environment} from "~/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  public getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(environment.apiUrl + 'posts/');
  }

  public getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(environment.apiUrl + 'posts/' + id);
  }

  public createPost(post: any): Observable<Post> {
    return this.http.post<Post>(environment.apiUrl + 'posts/', post);
  }

  public updatePost(post: Post): Observable<Post> {
    return this.http.put<Post>(environment.apiUrl + 'posts/' + post.id, post);
  }

  public deletePost(id: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + 'posts/' + id);
  }

  public getPostsByUser(username: string): Observable<Post[]> {
    return this.http.get<Post[]>(environment.apiUrl + 'posts/user/' + username);
  }

  // public toggleLike(id: number): Observable<Post> {
  //   return this.http.put<Post>(environment.apiUrl + 'posts/like/' + id, {});
  // }

  public toggleLike(post: Post): Observable<Post> {
    return this.http.put<Post>(environment.apiUrl + 'posts/like', post);
  }
}
