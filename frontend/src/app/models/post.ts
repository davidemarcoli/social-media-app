import {User} from "./user";
import {Category} from "./category";

export class Post {
  id: number;
  title: string;
  content: string;
  categories: Category[];
  author: User;
  createdAt: Date;
  updatedAt: Date;


  constructor(id: number, title: string, content: string, categories: Category[], author: User, createdAt: Date, updatedAt: Date) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.categories = categories;
    this.author = author;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
