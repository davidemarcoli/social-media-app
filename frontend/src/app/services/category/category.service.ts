import { Injectable } from '@angular/core';
import {Category} from "@models/category";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "~/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  public getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(environment.apiUrl + 'categories/');
  }

  public getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(environment.apiUrl + 'categories/' + id);
  }

  public createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(environment.apiUrl + 'categories/', category);
  }

  public updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(environment.apiUrl + 'categories/' + category.id, category);
  }

  public deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + 'categories/' + id);
  }

}
