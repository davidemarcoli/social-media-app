import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "@models/category";
import {CategoryService} from "@services/category/category.service";
import {AlertService} from "@services/alert/alert.service";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-delete-category',
  templateUrl: './delete-category.component.html',
  styleUrls: ['./delete-category.component.css']
})
export class DeleteCategoryComponent implements OnInit {

  // @ts-ignore
  form: FormGroup;

  categoryList: Category[] = [];

  constructor(private categoryService: CategoryService, private alertService: AlertService) {

    const category$ = this.categoryService.getCategories();
    lastValueFrom(category$).then(value => {
      this.categoryList = value || [];
    })
  }

  onCategoryChange(event: any) {
    console.log(event)
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      categoryId: new FormControl('', Validators.required),
    });
  }

  get categoryId() {
    return this.form.get('oldCategoryId');
  }


  onSubmit() {
    const category$ = this.categoryService.deleteCategory(this.form.value.categoryId);
    lastValueFrom(category$).then(() => {
      this.alertService.success("Category deleted successfully");
      location.reload();
    })
      .catch(reason => {
        this.alertService.error(reason.error.message);
      })
  }

}
