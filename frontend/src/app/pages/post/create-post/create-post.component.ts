import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "@services/category/category.service";
import {AlertService} from "@services/alert/alert.service";
import {Category} from "@models/category";
import {PostService} from "@services/post/post.service";
import {Post} from "@models/post";

// @ts-ignore
import * as CustomEditor from '@leo1305/ckeditor5-build-custom';
import {Router} from "@angular/router";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-create-port',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  public Editor = CustomEditor;

  // @ts-ignore
  form: FormGroup;

  categoryList: Category[] = [];

  constructor(private categoryService: CategoryService, private postService: PostService, private alertService: AlertService, private router: Router) {
    const categories$ = this.categoryService.getCategories();
    lastValueFrom(categories$).then(value => {
      this.categoryList = value || [];
    });
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      title: new FormControl('', Validators.required),
      categories: new FormControl([], Validators.required),
      content: new FormControl('', Validators.required)
    });
  }

  get title() {
    return this.form.get('title');
  }

  get content() {
    return this.form.get('content');
  }

  get categories() {
    return this.form.get('categories');
  }

  onSubmit() {
    console.log(this.form)

    let post = {
      title: this.form.value.title,
      content: this.form.value.content,
      categories: this.categoryList.filter(value => this.form.value.categories.includes(value.id.toString()))
    } as Post;

    const post$ = this.postService.createPost(post);
    lastValueFrom(post$).then(() => {
        this.alertService.success('Post created successfully');
        this.router.navigateByUrl('home');
      }
    ).catch(reason => {
        this.alertService.error(reason.error.message);
      }
    );
  }

}
