import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "@models/category";
import {CategoryService} from "@services/category/category.service";
import {AlertService} from "@services/alert/alert.service";
import {Router} from "@angular/router";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit {

  // @ts-ignore
  public form: FormGroup;

  public oldCategory: Category | undefined;

  categoryList: Category[] = [];


  constructor(private categoryService: CategoryService, private alertService: AlertService, private router: Router) {
    const category$ = this.categoryService.getCategories();
    lastValueFrom(category$).then(value => {
      this.categoryList = value || [];
    })
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      oldCategory: new FormControl(undefined, Validators.required),
      name: new FormControl('', Validators.required),
      color: new FormControl('', Validators.required),
    });

    this.form.get('oldCategory')?.valueChanges.subscribe(value => {
      this.oldCategory = value;
      this.form.patchValue({
        name: value.name,
        color: value.color,
      })
    });
  }

  get oldCategoryId() {
    return this.form.get('oldCategoryId');
  }

  get name() {
    return this.form.get('name');
  }

  get color() {
    return this.form.get('color');
  }

  getPreviewCategory() {
    return new Category(0, this.form.value.name,  this.form.value.color);
  }

  onSubmit() {
    const category$ = this.categoryService.updateCategory(new Category(this.oldCategory?.id, this.form.value.name, this.form.value.color));
    lastValueFrom(category$)
      .then(() => {
        this.alertService.success('Category edited successfully');
        this.router.navigateByUrl('/');
      })
      .catch(reason => {
        this.alertService.error(reason.error.message);
      });
  }

}
