import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "@models/category";
import {CategoryService} from "@services/category/category.service";
import {PostService} from "@services/post/post.service";
import {AlertService} from "@services/alert/alert.service";
import {Post} from "@models/post";

// @ts-ignore
import * as CustomEditor from '@leo1305/ckeditor5-build-custom';
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "@models/user";
import {AuthService} from "@services/auth/auth.service";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrls: ['./edit-post.component.css']
})
export class EditPostComponent implements OnInit {

  public Editor = CustomEditor;

  // @ts-ignore
  form: FormGroup;

  oldPost: Post = new Post(0, "", "", [], new User("", "", "", ""), new Date(), new Date());
  categoryList: Category[] = [];

  constructor(private categoryService: CategoryService, private postService: PostService, private alertService: AlertService, private route: ActivatedRoute, private router: Router, private authService: AuthService) {
    const categories$ = this.categoryService.getCategories();
    lastValueFrom(categories$).then(value => {
      this.categoryList = value || [];
    });

    console.log(this.authService.getRoles())
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      title: new FormControl('', Validators.required),
      content: new FormControl('', Validators.required),
      categories: new FormControl([], Validators.required)
    });

    let id = this.route.snapshot.queryParams['id'];

    const post$ = this.postService.getPostById(id);
    lastValueFrom(post$).then(value => {

      if (!value) {
        this.alertService.error('Post not found');
        this.router.navigate(['/']);
        return;
      }

      this.oldPost = value;

      this.form.patchValue({
        title: value.title,
        content: value.content,
        categories: value.categories
      })
      this.form.updateValueAndValidity();

      if (!(this.authService.getRoles().includes('ROLE_MODERATOR') || this.oldPost.author.username === this.authService.getUsername())) {
        this.alertService.error('You are not allowed to edit this post');
        this.router.navigate(['/']);
      }
    })
      .catch(reason => {
        this.alertService.error(reason.error.message);
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

  onSubmit(event: any) {
    console.log(event)
    console.log(this.form)

    this.oldPost.title = this.form.value.title;
    this.oldPost.content = this.form.value.content;
    this.oldPost.categories = this.form.value.categories;

    const post$ = this.postService.updatePost(this.oldPost);
    lastValueFrom(post$).then(value => {
      console.log(value)
      this.alertService.success('Post saved successfully');
      this.router.navigateByUrl('home');
    })
      .catch(reason => {
        console.log(reason)
        this.alertService.error(reason.error.message);
      });
  };

  compareCategory(c1: Category, c2: Category): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  userIsAuthor() {
    return this.oldPost.author.username === this.authService.getUsername();
  }

}
