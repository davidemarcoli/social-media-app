import {User} from "./user";

export class Post {
  id: number;
  content: string;
  author: User;
  createdAt: Date;
  updatedAt: Date;
  media: number[];
  likes: User[];


  constructor(id: number, content: string, author: User, createdAt: Date, updatedAt: Date, media: number[], likes: User[]) {
    this.id = id;
    this.content = content;
    this.author = author;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.media = media;
    this.likes = likes;
  }
}
