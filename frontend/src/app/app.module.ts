import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { AppComponent } from './app.component'
import { LoginComponent } from '@pages/auth/login/login.component'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module'
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'
import { JwtInterceptor } from './helper/jwt.interceptor'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { SignupComponent } from '@pages/auth/signup/signup.component'
import { ErrorInterceptor } from './helper/error.interceptor'
import { CreatePostComponent } from '@pages/post/create-post/create-post.component'
import { ListPostsComponent } from '@pages/post/list-posts/list-posts.component'
import { CKEditorModule } from '@ckeditor/ckeditor5-angular'
import { ViewPostComponent } from '@pages/post/view-post/view-post.component'
import { ColorPickerModule } from 'ngx-color-picker'
import { TruncatePipe } from '~/app/helper/truncate.pipe'
import { UserProfileComponent } from './pages/user/user-profile/user-profile.component'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { CardComponent } from './components/card/card.component'
import { PostListComponent } from './components/post-list/post-list.component'
import { PostCardComponent } from './components/post-card/post-card.component'

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
    CardComponent,
    PostListComponent,
    PostCardComponent,
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    // MatFormFieldModule,
    // MatIconModule,
    // MatButtonModule,
    // MatInputModule,
    // MatToolbarModule,
    // MatSnackBarModule,
    // MatOptionModule,
    // MatSelectModule,
    // MatMenuModule,
    CKEditorModule,
    // MatCardModule,
    ColorPickerModule,
    FormsModule,
    FontAwesomeModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
