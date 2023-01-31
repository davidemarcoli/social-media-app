import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
// import {AlertService} from "@services/alert/alert.service";
import { Category } from '@models/category'
import { PostService } from '@services/post/post.service'
import { Post } from '@models/post'

// @ts-ignore
import * as CustomEditor from '@leo1305/ckeditor5-build-custom'
import { Router } from '@angular/router'
import { lastValueFrom } from 'rxjs'
import { date } from 'zod'
import { UserService } from '~/app/services/user/user.service'
import { AuthService } from '~/app/services/auth/auth.service'
import { User } from '~/app/models/user'

@Component({
  selector: 'app-create-port',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css'],
})
export class CreatePostComponent implements OnInit {
  public Editor = CustomEditor

  // @ts-ignore
  form: FormGroup
  mediaByteArray?: Array<number>

  constructor(
    private postService: PostService,
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      content: new FormControl('', Validators.required),
    })
  }

  get content() {
    return this.form.get('content')
  }

  async onSubmit() {
    let username = this.authService.getUsername()

    let post = {
      content: this.form.value.content,
      username: username,
      media: this.mediaByteArray,
    }

    const post$ = this.postService.createPost(post)
    lastValueFrom(post$)
      .then(() => {
        console.log('success')
        // this.alertService.success('Post created successfully')
        this.router.navigateByUrl('home')
      })
      .catch(reason => {
        console.log('ERRÖR')
        console.log(reason)
        // this.alertService.error(reason.error.message)
      })
  }

  onFileSelected(event: any) {
    const file = event.target.files[0]
    const fileBlob = new Blob([file])
    const fileReader = new FileReader()

    fileReader.readAsDataURL(fileBlob)
    fileReader.onload = event => {
      this.mediaByteArray = this.convertDataURIToBinary(
        event.target!.result as string,
      )
      console.log(this.mediaByteArray)
    }
  }

  private convertDataURIToBinary(dataURI: string) {
    var base64Index = dataURI.indexOf(';base64,') + ';base64,'.length
    var base64 = dataURI.substring(base64Index)
    var raw = window.atob(base64)
    var rawLength = raw.length
    var array = new Uint8Array(new ArrayBuffer(rawLength))

    for (let i = 0; i < rawLength; i++) {
      array[i] = raw.charCodeAt(i)
    }
    return Array.from(array)
  }
}
