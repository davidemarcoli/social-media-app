import {Component, Input} from '@angular/core';
import {Category} from "@models/category";

@Component({
  selector: 'app-category-pill[category]',
  templateUrl: './category-pill.component.html',
  styleUrls: ['./category-pill.component.css']
})
export class CategoryPillComponent {

  @Input() category!: Category;

}
