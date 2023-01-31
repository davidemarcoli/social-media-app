import { Input } from '@angular/core'
import { Component } from '@angular/core'
import { Post } from '~/app/models/post'
import { faHeart as OutlinedHeart } from '@fortawesome/free-regular-svg-icons'
import { faHeart as SolidHeart } from '@fortawesome/free-solid-svg-icons'
import { DateUtil } from '@utils/date.util'
import { PostService } from '@services/post/post.service'
import { AuthService } from '@services/auth/auth.service'
import { lastValueFrom } from 'rxjs'
import { DomSanitizer } from '@angular/platform-browser'

@Component({
  selector: 'dl-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css'],
})
export class PostCardComponent {
  readonly outlinedHeart = OutlinedHeart
  readonly solidHeart = SolidHeart

  @Input() post!: Post

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private sanitizer: DomSanitizer,
  ) {}

  ngOnInit(): void {}

  getSanitizedString(str: string) {
    return this.sanitizer.bypassSecurityTrustHtml(str)
  }

  getImage(post: Post) {
    let objectURL = 'data:image/png;base64,' + post.media
    return this.sanitizer.bypassSecurityTrustUrl(objectURL)
  }

  getHeartIcon(post: Post) {
    return this.hasCurrentUserLikedPost(post)
      ? this.solidHeart
      : this.outlinedHeart
  }

  onLikeClick(post: Post) {
    lastValueFrom(this.postService.toggleLike(post))
      .then(updatedPost => {
        this.post = updatedPost
        // this.alertService.success('Post liked')
      })
      .catch(error => {
        console.error(error)
        // this.alertService.error(error.error.message)
      })
  }

  hasCurrentUserLikedPost(post: Post) {
    if (post.likes)
      return post.likes.some(
        like => like.username === this.authService.getUsername(),
      )

    return false
  }

  getRelativeDate(date: Date) {
    return DateUtil.getRelativeDate(date)
  }
}
