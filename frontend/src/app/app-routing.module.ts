import {NgModule} from '@angular/core';
import {RouterModule, Routes, UrlSegment} from "@angular/router";
import {LoginComponent} from "@pages/auth/login/login.component";
import {AuthGuardService} from "@services/auth-guard/auth-guard.service";
import {SignupComponent} from "@pages/auth/signup/signup.component";
import {CreatePostComponent} from "@pages/post/create-post/create-post.component";
import {ListPostsComponent} from "@pages/post/list-posts/list-posts.component";
import {ViewPostComponent} from "@pages/post/view-post/view-post.component";
import {UserProfileComponent} from "@pages/user/user-profile/user-profile.component";

const routes: Routes = [
  {path: 'home', component: ListPostsComponent, canActivate: [AuthGuardService]},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {
    path: 'post', children: [
      {path: 'create', component: CreatePostComponent, canActivate: [AuthGuardService]},
      {path: 'list', component: ListPostsComponent, canActivate: [AuthGuardService]},
      {path: 'view', component: ViewPostComponent, canActivate: [AuthGuardService]}
    ]
  },
  {path: "profile/:username", component: UserProfileComponent, canActivate: [AuthGuardService]},
  {
    matcher: (url) => {
      console.log(url)
      // You can have regex as per your requirement
      if (url.length === 1 && url[0].path.match(/^@\w+$/gm)) {
        return {
          consumed: url,
          posParams: {
            username: new UrlSegment(url[0].path.substring(1), {}) // <--- creating UrlSegment by getting rid of @ from url path
          }
        };
      }

      return null;
    },
    component: UserProfileComponent,
    // redirectTo: 'profile/:username',
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {path: '**', redirectTo: 'home'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
