import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {LoginComponent} from '@pages/auth/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./helper/jwt.interceptor";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatToolbarModule} from "@angular/material/toolbar";
import {SignupComponent} from '@pages/auth/signup/signup.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatMenuModule} from "@angular/material/menu";
import {ErrorInterceptor} from "./helper/error.interceptor";
import {CreatePostComponent} from '@pages/post/create-post/create-post.component';
import {ListPostsComponent} from '@pages/post/list-posts/list-posts.component';
import {CKEditorModule} from "@ckeditor/ckeditor5-angular";
import {MatCardModule} from "@angular/material/card";
import {ViewPostComponent} from '@pages/post/view-post/view-post.component';
import {ColorPickerModule} from "ngx-color-picker";
import {MatTooltipModule} from "@angular/material/tooltip";
import {TruncatePipe} from "~/app/helper/truncate.pipe";
import {UserProfileComponent} from '@pages/user/user-profile/user-profile.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostCardComponent } from './components/post-card/post-card.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    CreatePostComponent,
    ListPostsComponent,
    ViewPostComponent,
    TruncatePipe,
    UserProfileComponent,
    PostListComponent,
    PostCardComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatOptionModule,
    MatSelectModule,
    MatMenuModule,
    CKEditorModule,
    MatCardModule,
    ColorPickerModule,
    MatTooltipModule,
    FormsModule,
    FontAwesomeModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
