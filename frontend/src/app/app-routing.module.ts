import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "@pages/auth/login/login.component";
import {AuthGuardService} from "@services/auth-guard/auth-guard.service";
import {SignupComponent} from "@pages/auth/signup/signup.component";
import {CreateCategoryComponent} from "@pages/category/create-category/create-category.component";
import {CreatePostComponent} from "@pages/post/create-post/create-post.component";
import {ListPostsComponent} from "@pages/post/list-posts/list-posts.component";
import {UpdateCategoryComponent} from "@pages/category/update-category/update-category.component";
import {DeleteCategoryComponent} from "@pages/category/delete-category/delete-category.component";
import {ViewPostComponent} from "@pages/post/view-post/view-post.component";
import {EditPostComponent} from "@pages/post/edit-post/edit-post.component";
import {AdminAuthGuardService} from "@services/admin-auth-guard/admin-auth-guard.service";

const routes: Routes = [
  { path: 'home', component: ListPostsComponent, canActivate: [AuthGuardService] },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {path: 'post', children: [
      {path: 'create', component: CreatePostComponent, canActivate: [AuthGuardService]},
      { path: 'edit', component: EditPostComponent, canActivate: [AuthGuardService] },
      {path: 'list', component: ListPostsComponent, canActivate: [AuthGuardService]},
      {path: 'view', component: ViewPostComponent, canActivate: [AuthGuardService]}
    ]},
  { path: 'category', canActivate: [AdminAuthGuardService], children: [
      { path: 'create', component: CreateCategoryComponent },
      { path: 'edit', component: UpdateCategoryComponent },
      { path: 'delete', component: DeleteCategoryComponent },
    ]},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
