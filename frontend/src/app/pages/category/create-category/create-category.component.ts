import {Component, OnInit} from '@angular/core';
import {CategoryService} from "@services/category/category.service";
import {Category} from "@models/category";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "@services/alert/alert.service";
import {Router} from "@angular/router";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit {

  // @ts-ignore
  form: FormGroup;

  constructor(private categoryService: CategoryService, private alertService: AlertService, private router: Router) {
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', Validators.required),
      color: new FormControl('', Validators.required),
    });

    this.form.valueChanges.subscribe(value => {
      console.log(value);
    }
    );
  }

  get name() {
    return this.form.get('name');
  }

  get color() {
    return this.form.get('color');
  }

  getPreviewCategory() {
    return new Category(0, this.form.value.name, this.form.value.color);
  }

  onSubmit() {
    const category$ = this.categoryService.createCategory(new Category(0, this.form.value.name, this.form.value.color));

    lastValueFrom(category$)
      .then(() => {
        this.alertService.success('Category created successfully');
        this.router.navigate(['/']);
      })
      .catch(reason => {
        console.log(reason)
        this.alertService.error(reason.error.message);
      });
  };

}
