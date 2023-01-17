import {User} from "./user";
import {Category} from "./category";

export class Post {
  id: number;
  content: string;
  author: User;
  createdAt: Date;
  updatedAt: Date;
  media: string;
  likes: User[];


  constructor(id: number, content: string, author: User, createdAt: Date, updatedAt: Date, media: string, likes: User[]) {
    this.id = id;
    this.content = content;
    this.author = author;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.media = media;
    this.likes = likes;
  }
}
