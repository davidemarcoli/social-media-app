import {Post} from "@models/post";

export class User {
  id: string;
  email: string;
  username: string;
  password: string;
  profilePictureURL: string;
  roles: {id: number, name: string}[];
  posts: Post[];
  likedPosts: Post[];


  constructor(id: string, email: string, username: string, password: string, profilePictureURL: string, roles: { id: number; name: string }[], posts: Post[], likedPosts: Post[]) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.password = password;
    this.profilePictureURL = profilePictureURL;
    this.roles = roles;
    this.posts = posts;
    this.likedPosts = likedPosts;
  }
}
